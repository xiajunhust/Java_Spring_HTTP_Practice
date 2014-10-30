/**
 * XXX.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.xiajun.test.javanio.ROXJavaNIO;

import java.nio.channels.SocketChannel;

/**
 * From : http://rox-xmlrpc.sourceforge.net/niotut/index.html
 * 
 * @author xiajun.xj
 * @version $Id: ServerDataEvent.java, v 0.1 2014年10月30日 下午3:02:03 xiajun.xj Exp $
 */
class ServerDataEvent {
    public NioServer     server;
    public SocketChannel socket;
    public byte[]        data;

    public ServerDataEvent(NioServer server, SocketChannel socket, byte[] data) {
        this.server = server;
        this.socket = socket;
        this.data = data;
    }
}
