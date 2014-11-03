package com.xiajun.test.javanio.NioMultiThreadServer;

import java.util.ArrayList;

/**
 * <p>Title: 事件触发器</p>
 * 用于在适当的时候通过触发服务器事件，通知在册的事件处理器对事件做出响应。
 * 注意：是单例模式。
 * 
 * @author starboy
 * @version 1.0
 */
public class Notifier {
    private static ArrayList listeners = null;
    private static Notifier  instance  = null;

    /**
     * 构造函数
     */
    private Notifier() {
        listeners = new ArrayList();
    }

    /**
     * 获取事件触发器
     * @return 返回事件触发器
     */
    public static synchronized Notifier getNotifier() {
        if (instance == null) {
            instance = new Notifier();
            return instance;
        } else
            return instance;
    }

    /**
     * 添加事件监听器
     * @param l 监听器
     */
    public void addListener(ServerListener l) {
        synchronized (listeners) {
            if (!listeners.contains(l))
                listeners.add(l);
        }
    }

    /**
     * 有连接事件时
     * 
     * @throws Exception
     */
    public void fireOnAccept() throws Exception {
        //调用每一个事件监听器的onAccept方法
        for (int i = listeners.size() - 1; i >= 0; i--)
            ((ServerListener) listeners.get(i)).onAccept();
    }

    /**
     * 连接后的事件
     * 
     * @param request
     * @throws Exception
     */
    public void fireOnAccepted(Request request) throws Exception {
        //调用每一个事件监听器的onAccepted方法
        for (int i = listeners.size() - 1; i >= 0; i--)
            ((ServerListener) listeners.get(i)).onAccepted(request);
    }

    /**
     * 当客户端发来数据，并已被服务器控制线程正确读取时，触发此事件。
     * 
     * @param request
     * @throws Exception
     */
    void fireOnRead(Request request) throws Exception {
        for (int i = listeners.size() - 1; i >= 0; i--)
            ((ServerListener) listeners.get(i)).onRead(request);

    }

    /**
     * 当客户端可以开始接收服务端发送数据时触发此事件。
     * 
     * @param request
     * @param response
     * @throws Exception
     */
    void fireOnWrite(Request request, Response response) throws Exception {
        for (int i = listeners.size() - 1; i >= 0; i--)
            ((ServerListener) listeners.get(i)).onWrite(request, response);

    }

    /**
     * 客户端可以开始接收服务端发送数据时触发该事件。
     * 
     * @param request
     * @throws Exception
     */
    public void fireOnClosed(Request request) throws Exception {
        for (int i = listeners.size() - 1; i >= 0; i--)
            ((ServerListener) listeners.get(i)).onClosed(request);
    }

    /**
     * 当客户端与服务器从连接开始到最后断开连接期间发生错误时触发该事件。
     * 
     * @param error
     */
    public void fireOnError(String error) {
        for (int i = listeners.size() - 1; i >= 0; i--)
            ((ServerListener) listeners.get(i)).onError(error);
    }
}
