package com.alipay.xiajun.SpringTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class SimpleTest {
    private static String       log4jFileName = "src/config/log4j.properties";
    public static String        xmlFileName   = "src/config/ApplicationContext.xml";
    private static final Logger logger        = LoggerFactory.getLogger(SimpleTest.class);

    public static void main(String[] args) {
        configProperty();

        //new FileSystemXmlApplicationContext(xmlFileName);

        //        ApplicationContext ctx = new FileSystemXmlApplicationContext(xmlFileName);
        //        static Scheduler schedulerFactory = (Scheduler) ctx.getBean("schedulerFactory");

        Scheduler scheduler = SchedulerSingleton.getInstance();

        //设置一个线程，每隔一段时间更改cron表达式
        CronChangeThread thread = new CronChangeThread("thread1");
        thread.start();
    }

    /**
     * config properties
     * 
     */
    private static void configProperty() {
        Properties properties = new Properties();
        FileInputStream istream;
        try {
            istream = new FileInputStream(log4jFileName);
            properties.load(istream);
            istream.close();
        } catch (FileNotFoundException e) {
            logger.error("File not found", e);
        } catch (IOException e) {
            logger.error("load file erroe", e);
        } finally {

        }

        //properties.setProperty("log4j.appender.file.File",logFile); 
        PropertyConfigurator.configure(properties);
    }
}
