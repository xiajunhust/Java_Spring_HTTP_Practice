/**
 * XXX.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.xiajun.test.javanio.ReactorSingleThread;

import java.io.IOException;

/**
 * 服务端
 * 
 * @author xiajun.xj
 * @version $Id: Server.java, v 0.1 2014年11月6日 下午8:38:31 xiajun.xj Exp $
 */
public class Server {
    static final int PORT = 8001;

    public static void main(String[] args) throws IOException {

        Reactor reactor = new Reactor(PORT);

        reactor.run();
    }
}
