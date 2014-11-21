/**
 * XXX.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.xiajun.test.javanio.ReactorSingleThread;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Reactor
 * 
 * @author xiajun.xj
 * @version $Id: Reactor.java, v 0.1 2014��11��6�� ����8:03:09 xiajun.xj Exp $
 */
public class Reactor implements Runnable {
    final Selector            selector;
    final ServerSocketChannel serverSocket;

    Reactor(int port) throws IOException {
        selector = Selector.open();
        serverSocket = ServerSocketChannel.open();
        serverSocket.socket().bind(new InetSocketAddress(port));
        serverSocket.configureBlocking(false);
        SelectionKey sk = serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        sk.attach(new Acceptor());
    }

    /*
    lternatively, use explicit SPI provider:
    SelectorProvider p = SelectorProvider. provider() ;
    selector = p. openSelector() ;
    serverSocket = p. openServerSocketChannel() ;
    */

    public void run() { // normally in a new  Thread
        System.out.println("--Reactor����");
        try {
            while (!Thread.interrupted()) {
                selector.select();
                Set selected = selector.selectedKeys();
                Iterator it = selected.iterator();
                while (it.hasNext()) {
                    SelectionKey sk = (SelectionKey) (it.next());
                    if (sk.isAcceptable() || sk.isReadable()) {
                        dispatch(sk);
                    }
                }

                selected.clear();
            }
        } catch (IOException ex) { /* . . . */
        }
    }

    //�������¼�
    void dispatch(SelectionKey k) {
        Runnable r = (Runnable) (k.attachment());
        if (r != null)
            r.run();
    }

    //�������Ӵ���
    class Acceptor implements Runnable {
        public void run() {
            try {
                System.out.println("--��������");
                SocketChannel c = serverSocket.accept();
                if (c != null)
                    new Handler(selector, c);
            } catch (IOException ex) {
                /* . . . */
            }
        }
    }
}
