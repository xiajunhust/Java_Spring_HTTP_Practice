/**
 * XXX.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.alipay.xiajun.SpringTest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author xiajun.xj
 * @version $Id: CronChangeThread.java, v 0.1 2014��12��10�� ����8:59:24 xiajun.xj Exp $
 */
public class CronChangeThread extends Thread {
    private static final Logger logger = LoggerFactory.getLogger(CronChangeThread.class);

    public CronChangeThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            logger.error("�����ڼ��׳��쳣", e);
        }
        SimpleTask.cronExpression = "0/5 * * * * ?";

        logger.info("����cron���ʽ");
    }
}
