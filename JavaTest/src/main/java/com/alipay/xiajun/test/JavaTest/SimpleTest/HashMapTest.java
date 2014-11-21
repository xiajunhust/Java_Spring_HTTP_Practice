/**
 * XXX.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.alipay.xiajun.test.JavaTest.SimpleTest;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author xiajun.xj
 * @version $Id: HashMapTest.java, v 0.1 2014年11月21日 上午11:52:22 xiajun.xj Exp $
 */
public class HashMapTest {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "xia");
        map.put("2", "jun");

        String value = map.get("2");
        map.remove("2");
        map.remove("3");
        System.out.println(map);
        System.out.println(value);
    }

}
