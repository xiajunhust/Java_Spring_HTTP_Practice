/**
 * XXX.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.alipay.xiajun.test.JavaTest.SoftReferenceCache;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.Hashtable;

/**
 * Person类软引用缓存实现
 * 
 * @author xiajun.xj
 * @version $Id: PersonWithCache.java, v 0.1 2014年11月21日 下午3:14:52 xiajun.xj Exp $
 */
public class PersonWithCache {
    //单例
    static private PersonWithCache personWithCache;
    //存储缓存内容
    private final Hashtable        personRefsHashTable;
    //软引用队列
    private final ReferenceQueue   referenceQueue;

    /**
     * 构造函数[私有]
     */
    private PersonWithCache() {
        personRefsHashTable = new Hashtable();
        referenceQueue = new ReferenceQueue();
    }

    /**
     * 得到PersonWithCache实例
     * 
     * @return
     */
    public static PersonWithCache getInstance() {
        if (personWithCache == null) {
            personWithCache = new PersonWithCache();
        }

        return personWithCache;
    }

    /**
     * Person的软引用类型
     * 
     * @author xiajun.xj
     * @version $Id: PersonWithCache.java, v 0.1 2014年11月21日 下午4:11:45 xiajun.xj Exp $
     */
    private class PersonRef extends SoftReference<Person> {
        private String key = "";

        public PersonRef(Person person, ReferenceQueue<Person> queue) {
            super(person, queue);
            this.key = person.getId();
        }

        public String getKey() {
            return key;
        }
    }

    /**
     * 根据ID号，得到Person对象
     * 
     * @return
     * @throws InterruptedException 
     */
    public Person getPerson(String ID) throws InterruptedException {
        Person person = null;

        //判断缓存中是否有该Person实例的软引用，如果有，直接从缓存中软引用中取。
        if (personRefsHashTable.containsKey(ID)) {
            PersonRef personRef = (PersonRef) personRefsHashTable.get(ID);
            //从软引用中得到实例
            person = personRef.get();

            //System.out.println("Congratulations！Cache Hit!");
        }

        //System.out.println("Sorry, Cache missed! Now get it from DB");

        //如果软引用中没有，或者从软引用中取到的是null，则从数据库中取数据，并且保存对其的软引用
        if (person == null) {
            person = getInfoFromDB(ID);
            cachePerson(person);
        }

        return person;
    }

    /**
     * 清除缓存中全部内容
     */
    public void clearCache() {
        cleanCache();
        personRefsHashTable.clear();
        System.gc();
        System.runFinalization();
    }

    /**
     * 以软引用的方式对一个Person对象的实例进行引用并保存该引用
     * 
     * @param person
     */
    private void cachePerson(Person person) {
        //首先被清除操作
        cleanCache();
        PersonRef personRef = new PersonRef(person, referenceQueue);
        personRefsHashTable.put(person.getId(), personRef);
    }

    /**
     * 清除软引用对象已经被回收了的Person对象
     * 
     */
    private void cleanCache() {
        PersonRef personRef = null;
        while ((personRef = (PersonRef) (referenceQueue.poll())) != null) {
            personRefsHashTable.remove(personRef.getKey());

        }
    }

    /**
     * 模拟从数据库中取数据
     * @throws InterruptedException 
     */
    private Person getInfoFromDB(String ID) throws InterruptedException {
        //睡眠10ms，模拟读取数据库
        Thread.sleep(10);

        Person person = new Person(ID);
        return person;
    }
}
