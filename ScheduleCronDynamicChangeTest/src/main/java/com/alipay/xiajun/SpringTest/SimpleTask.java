/**
 * XXX.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.alipay.xiajun.SpringTest;

import java.text.ParseException;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.CronTriggerBean;

/**
 * 
 * @author xiajun.xj
 * @version $Id: SimpleTask.java, v 0.1 2014年12月10日 下午8:29:09 xiajun.xj Exp $
 */
public class SimpleTask {
    private static final Logger logger         = LoggerFactory.getLogger(SimpleTest.class);

    public static String        cronExpression = "0/10 * * * * ?";

    public void doTask() throws SchedulerException, ParseException {
        logger.info("Simple task running");

        Scheduler scheduler = SchedulerSingleton.getInstance();

        CronTriggerBean trigger = (CronTriggerBean) scheduler.getTrigger("cronTrigger",
            Scheduler.DEFAULT_GROUP);

        if (!trigger.getCronExpression().equalsIgnoreCase(cronExpression)) {

            trigger.setCronExpression(cronExpression);

            scheduler.rescheduleJob("cronTrigger", Scheduler.DEFAULT_GROUP, trigger);

            logger.info("设置新的cron表达式到cronTrigger");
        }
    }
}
