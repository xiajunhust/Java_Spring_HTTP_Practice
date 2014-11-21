/**
 * XXX.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.alipay.xiajun.test.JavaTest.SimpleTest;

/**
 * 
 * @author xiajun.xj
 * @version $Id: ValueReferecceTest.java, v 0.1 2014年11月21日 下午2:26:41 xiajun.xj Exp $
 */
public class ValueReferecceTest {
    public static void main(String[] args) {
        Point pnt1 = new Point(0, 0);
        Point pnt2 = new Point(0, 0);
        System.out.println("X: " + pnt1.x + " Y: " + pnt1.y);
        System.out.println("X: " + pnt2.x + " Y: " + pnt2.y);
        System.out.println(" ");
        tricky(pnt1, pnt2);
        System.out.println("X: " + pnt1.x + " Y:" + pnt1.y);
        System.out.println("X: " + pnt2.x + " Y: " + pnt2.y);
    }

    public static void tricky(Point arg1, Point arg2) {
        arg1.x = 100;
        arg1.y = 100;
        Point temp = arg1;
        arg1 = arg2;
        arg2 = temp;
    }
}
