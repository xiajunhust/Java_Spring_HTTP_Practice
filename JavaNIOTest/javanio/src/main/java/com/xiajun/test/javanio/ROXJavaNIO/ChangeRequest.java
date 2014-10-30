/**
 * XXX.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.xiajun.test.javanio.ROXJavaNIO;

import java.nio.channels.SocketChannel;

/**
 * 展现对特定socket channel的变更
 * From : http://rox-xmlrpc.sourceforge.net/niotut/index.html
 * 
 * @author xiajun.xj
 * @version $Id: ChangeRequest.java, v 0.1 2014年10月30日 下午2:43:46 xiajun.xj Exp $
 */
public class ChangeRequest {
    public static final int REGISTER  = 1;
    public static final int CHANGEOPS = 2;

    public SocketChannel    socket;
    public int              type;
    public int              ops;

    public ChangeRequest(SocketChannel socket, int type, int ops) {
        this.socket = socket;
        this.type = type;
        this.ops = ops;
    }
}
