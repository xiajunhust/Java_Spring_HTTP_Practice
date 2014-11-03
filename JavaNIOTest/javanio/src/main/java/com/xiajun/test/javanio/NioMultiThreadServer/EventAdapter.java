package com.xiajun.test.javanio.NioMultiThreadServer;

/**
 * <p>Title: 事件适配器</p>
 * 目的是让最终实现的事件处理器只处理所关心的事件。
 * 
 * @author starboy
 * @version 1.0
 */

public abstract class EventAdapter implements ServerListener {
    public EventAdapter() {
    }

    public void onError(String error) {
    }

    public void onAccept() throws Exception {
    }

    public void onAccepted(Request request) throws Exception {
    }

    public void onRead(Request request) throws Exception {
    }

    public void onWrite(Request request, Response response) throws Exception {
    }

    public void onClosed(Request request) throws Exception {
    }
}
