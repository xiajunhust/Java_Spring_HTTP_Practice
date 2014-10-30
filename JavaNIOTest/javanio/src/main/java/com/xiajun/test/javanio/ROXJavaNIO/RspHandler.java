/**
 * XXX.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.xiajun.test.javanio.ROXJavaNIO;

/**
 * From : http://rox-xmlrpc.sourceforge.net/niotut/index.html
 * 
 * @author xiajun.xj
 * @version $Id: RspHandler.java, v 0.1 2014年10月30日 下午3:04:33 xiajun.xj Exp $
 */

public class RspHandler {
    private byte[] rsp = null;

    public synchronized boolean handleResponse(byte[] rsp) {
        this.rsp = rsp;
        this.notify();
        return true;
    }

    public synchronized void waitForResponse() {
        while (this.rsp == null) {
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }

        System.out.println(new String(this.rsp));
    }
}
