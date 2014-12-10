/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.alipay.xiajun.test.JavaTest.SimpleTest;

import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.Object;

/**
 * 
 * @author xiajun.xj
 * @version $Id: EqualTest.java, v 0.1 2014年11月23日 下午11:19:42 xiajun.xj Exp $
 */
public class EqualTest {
    public static void main(String[] args) {
        List<String> list1 = new ArrayList<String>();
        list1.add("xia");

        List<String> list2 = new ArrayList<String>();
        list2.add("xia");

    }

    public static boolean eq(Object o1, Object o2) {
        if (o1.equals(o2)) {
            return true;
        }
        return false;

    }
}
