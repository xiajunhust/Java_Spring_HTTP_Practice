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
 * @version $Id: SoftReferenceCacheTest.java, v 0.1 2014��11��21�� ����4:43:53 xiajun.xj Exp $
 */
public class SoftReferenceCacheTest {
    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        //�������һЩ��Ҫ��ѯ��Person��ID
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

        System.out.println("����������ʱ��(�л���)��" + String.valueOf(endTime - startTime));
        System.out.println("����������ʱ��(�޻���)��" + String.valueOf(10 * 1500));
    }
}
