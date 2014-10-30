/**
 * XXX.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.xiajun.test.javanio.ROXJavaNIO;

import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.List;

/**
 * 处理数据的工作线程
 * 
 * From : http://rox-xmlrpc.sourceforge.net/niotut/index.html
 * 
 * @author xiajun.xj
 * @version $Id: EchoWorker.java, v 0.1 2014年10月30日 下午2:32:21 xiajun.xj Exp $
 */
public class EchoWorker implements Runnable {
    private final List queue = new LinkedList();

    public void processData(NioServer server, SocketChannel socket, byte[] data, int count) {
        byte[] dataCopy = new byte[count];
        System.arraycopy(data, 0, dataCopy, 0, count);
        synchronized (queue) {
            queue.add(new ServerDataEvent(server, socket, dataCopy));
            queue.notify();
        }
    }

    public void run() {
        ServerDataEvent dataEvent;

        while (true) {
            // Wait for data to become available
            synchronized (queue) {
                while (queue.isEmpty()) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                    }
                }
                dataEvent = (ServerDataEvent) queue.remove(0);
            }

            // Return to sender
            dataEvent.server.send(dataEvent.socket, dataEvent.data);
        }
    }
}