/**
 * XXX.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.alipay.xiajun.test.JavaTest.SimpleTest;

import java.util.Date;

/**
 * 
 * @author xiajun.xj
 * @version $Id: StringTest.java, v 0.1 2014年10月13日 下午3:08:51 xiajun.xj Exp $
 */
public class StringTest {
    public static void main(String[] args) {
        //字符串数组赋值，必须要有花括号
        String[] stringArr = { "test" };
        System.out.println(stringArr.length);

        Date date = new Date();
        System.out.println(date.toString());
        System.out.println(date.getTime());

        String s1 = "xia";
        String s2 = null;
        String s3 = s1 + s2;
        System.out.println(s3);
    }
}
