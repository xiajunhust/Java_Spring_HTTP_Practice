/**
 * XXX.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.xiajun.test.javanio.ReactorSingleThread;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * 客户端
 * 
 * @author xiajun.xj
 * @version $Id: Client.java, v 0.1 2014年10月30日 上午10:44:17 xiajun.xj Exp $
 */
public class Client {
    static final int PORT = 8001;

    public static void main(String[] args) throws IOException {
        new Client().start();
    }

    public void start() throws IOException {
        SocketChannel sc = SocketChannel.open();
        sc.configureBlocking(false);
        sc.connect(new InetSocketAddress("localhost", PORT));
        Selector selector = Selector.open();
        sc.register(selector, SelectionKey.OP_CONNECT);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            System.out.println("keys=" + keys.size());
            Iterator<SelectionKey> keyIterator = keys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                keyIterator.remove();
                if (key.isConnectable()) {
                    sc.finishConnect();
                    sc.register(selector, SelectionKey.OP_WRITE);
                    System.out.println("客户端与服务器建立连接完成...");
                    break;
                } else if (key.isWritable()) {
                    System.out.println("请输入数据");
                    String message = scanner.nextLine();
                    ByteBuffer writeBuffer = ByteBuffer.wrap(message.getBytes());
                    sc.write(writeBuffer);
                }
            }
        }
    }

}
