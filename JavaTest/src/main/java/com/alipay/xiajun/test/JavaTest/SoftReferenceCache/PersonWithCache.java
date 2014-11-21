/**
 * XXX.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.alipay.xiajun.test.JavaTest.SoftReferenceCache;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.Hashtable;

/**
 * Person�������û���ʵ��
 * 
 * @author xiajun.xj
 * @version $Id: PersonWithCache.java, v 0.1 2014��11��21�� ����3:14:52 xiajun.xj Exp $
 */
public class PersonWithCache {
    //����
    static private PersonWithCache personWithCache;
    //�洢��������
    private final Hashtable        personRefsHashTable;
    //�����ö���
    private final ReferenceQueue   referenceQueue;

    /**
     * ���캯��[˽��]
     */
    private PersonWithCache() {
        personRefsHashTable = new Hashtable();
        referenceQueue = new ReferenceQueue();
    }

    /**
     * �õ�PersonWithCacheʵ��
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
     * Person������������
     * 
     * @author xiajun.xj
     * @version $Id: PersonWithCache.java, v 0.1 2014��11��21�� ����4:11:45 xiajun.xj Exp $
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
     * ����ID�ţ��õ�Person����
     * 
     * @return
     * @throws InterruptedException 
     */
    public Person getPerson(String ID) throws InterruptedException {
        Person person = null;

        //�жϻ������Ƿ��и�Personʵ���������ã�����У�ֱ�Ӵӻ�������������ȡ��
        if (personRefsHashTable.containsKey(ID)) {
            PersonRef personRef = (PersonRef) personRefsHashTable.get(ID);
            //���������еõ�ʵ��
            person = personRef.get();

            //System.out.println("Congratulations��Cache Hit!");
        }

        //System.out.println("Sorry, Cache missed! Now get it from DB");

        //�����������û�У����ߴ���������ȡ������null��������ݿ���ȡ���ݣ����ұ�������������
        if (person == null) {
            person = getInfoFromDB(ID);
            cachePerson(person);
        }

        return person;
    }

    /**
     * ���������ȫ������
     */
    public void clearCache() {
        cleanCache();
        personRefsHashTable.clear();
        System.gc();
        System.runFinalization();
    }

    /**
     * �������õķ�ʽ��һ��Person�����ʵ���������ò����������
     * 
     * @param person
     */
    private void cachePerson(Person person) {
        //���ȱ��������
        cleanCache();
        PersonRef personRef = new PersonRef(person, referenceQueue);
        personRefsHashTable.put(person.getId(), personRef);
    }

    /**
     * ��������ö����Ѿ��������˵�Person����
     * 
     */
    private void cleanCache() {
        PersonRef personRef = null;
        while ((personRef = (PersonRef) (referenceQueue.poll())) != null) {
            personRefsHashTable.remove(personRef.getKey());

        }
    }

    /**
     * ģ������ݿ���ȡ����
     * @throws InterruptedException 
     */
    private Person getInfoFromDB(String ID) throws InterruptedException {
        //˯��10ms��ģ���ȡ���ݿ�
        Thread.sleep(10);

        Person person = new Person(ID);
        return person;
    }
}
