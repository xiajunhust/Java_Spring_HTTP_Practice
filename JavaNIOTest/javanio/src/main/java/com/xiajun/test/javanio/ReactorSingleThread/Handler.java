/**
 * XXX.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.xiajun.test.javanio.ReactorSingleThread;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * ������������
 * 
 * @author xiajun.xj
 * @version $Id: Handler.java, v 0.1 2014��11��6�� ����8:07:24 xiajun.xj Exp $
 */
public class Handler implements Runnable {
    final static int    MAXIN  = 1024;
    final static int    MAXOUT = 1024;
    final SocketChannel socket;
    final SelectionKey  sk;
    ByteBuffer          input  = ByteBuffer.allocate(MAXIN);
    ByteBuffer          output = ByteBuffer.allocate(MAXOUT);
    static final int    READING = 0, SENDING = 1;
    int                 state   = READING;

    Handler(Selector sel, SocketChannel c) throws IOException {
        socket = c;
        c.configureBlocking(false);
        // Optionally try first read now
        sk = socket.register(sel, 0);
        //�ٴ�attach�����´��ж��¼���ʱ��ֱ�Ӷ�����
        sk.attach(this);
        sk.interestOps(SelectionKey.OP_READ);
        sel.wakeup();
    }

    boolean inputIsComplete() {
        /* . . . */
        return true;
    }

    boolean outputIsComplete() {
        /* . . . */
        return true;
    }

    void process() {
        /* . . . */
    }

    public void run() {
        System.out.println("--Handler����");
        try {
            if (state == READING)
                read();
            else if (state == SENDING) {
                send();
                state = READING;
            }
        } catch (IOException ex) { /* . . . */
        }
    }

    void read() throws IOException {
        int numRead = socket.read(input);
        byte[] newBytes = new byte[numRead];
        System.arraycopy(input.array(), 0, newBytes, 0, numRead);
        System.out.println("--Handler�����ݣ�" + new String(newBytes));
        if (inputIsComplete()) {
            process();
            state = SENDING;
            // Normally also do first write now
            sk.interestOps(SelectionKey.OP_WRITE);
        }
    }

    void send() throws IOException {
        String response = "Get your message";
        byte bytes[] = response.getBytes();
        int numWrite = socket.write(input);
        System.out.println("--Handlerд����" + new String(bytes));
        /*        if (outputIsComplete())
                    sk.cancel();*/
    }
}
