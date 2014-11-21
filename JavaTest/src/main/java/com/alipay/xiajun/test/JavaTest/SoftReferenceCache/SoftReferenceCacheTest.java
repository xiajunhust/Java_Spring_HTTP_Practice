/**
 * XXX.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.alipay.xiajun.test.JavaTest.SoftReferenceCache;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 
 * @author xiajun.xj
 * @version $Id: SoftReferenceCacheTest.java, v 0.1 2014年11月21日 下午4:43:53 xiajun.xj Exp $
 */
public class SoftReferenceCacheTest {
    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        //随机生成一些需要查询的Person的ID
        List<String> IDList = new ArrayList<String>();
        for (int i = 0; i < 1500; i++) {
            IDList.add(String.valueOf(random.nextInt() % 1000));
        }

        PersonWithCache personWithCache = PersonWithCache.getInstance();

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < IDList.size(); i++) {
            personWithCache.getPerson(IDList.get(i));
        }

        long endTime = System.currentTimeMillis();

        personWithCache.clearCache();

        System.out.println("所经历的总时间(有缓存)：" + String.valueOf(endTime - startTime));
        System.out.println("所经历的总时间(无缓存)：" + String.valueOf(10 * 1500));
    }
}
