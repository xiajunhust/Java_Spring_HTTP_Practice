/**
 * XXX.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.alipay.xiajun.SpringTest;

import org.quartz.Scheduler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * 
 * @author xiajun.xj
 * @version $Id: SchedulerSingleton.java, v 0.1 2014年12月10日 下午9:46:00 xiajun.xj Exp $
 */
public class SchedulerSingleton {
    private static Scheduler scheduler;

    public static Scheduler getInstance() {
        if (scheduler == null) {
            System.out.println("加载XML文件");
            ApplicationContext ctx = new FileSystemXmlApplicationContext(SimpleTest.xmlFileName);
            scheduler = (Scheduler) ctx.getBean("schedulerFactory");
        }

        return scheduler;
    }
}
