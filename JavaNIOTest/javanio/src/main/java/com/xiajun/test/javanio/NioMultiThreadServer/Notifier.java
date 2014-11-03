package com.xiajun.test.javanio.NioMultiThreadServer;

import java.util.ArrayList;

/**
 * <p>Title: �¼�������</p>
 * �������ʵ���ʱ��ͨ�������������¼���֪ͨ�ڲ���¼����������¼�������Ӧ��
 * ע�⣺�ǵ���ģʽ��
 * 
 * @author starboy
 * @version 1.0
 */
public class Notifier {
    private static ArrayList listeners = null;
    private static Notifier  instance  = null;

    /**
     * ���캯��
     */
    private Notifier() {
        listeners = new ArrayList();
    }

    /**
     * ��ȡ�¼�������
     * @return �����¼�������
     */
    public static synchronized Notifier getNotifier() {
        if (instance == null) {
            instance = new Notifier();
            return instance;
        } else
            return instance;
    }

    /**
     * ����¼�������
     * @param l ������
     */
    public void addListener(ServerListener l) {
        synchronized (listeners) {
            if (!listeners.contains(l))
                listeners.add(l);
        }
    }

    /**
     * �������¼�ʱ
     * 
     * @throws Exception
     */
    public void fireOnAccept() throws Exception {
        //����ÿһ���¼���������onAccept����
        for (int i = listeners.size() - 1; i >= 0; i--)
            ((ServerListener) listeners.get(i)).onAccept();
    }

    /**
     * ���Ӻ���¼�
     * 
     * @param request
     * @throws Exception
     */
    public void fireOnAccepted(Request request) throws Exception {
        //����ÿһ���¼���������onAccepted����
        for (int i = listeners.size() - 1; i >= 0; i--)
            ((ServerListener) listeners.get(i)).onAccepted(request);
    }

    /**
     * ���ͻ��˷������ݣ����ѱ������������߳���ȷ��ȡʱ���������¼���
     * 
     * @param request
     * @throws Exception
     */
    void fireOnRead(Request request) throws Exception {
        for (int i = listeners.size() - 1; i >= 0; i--)
            ((ServerListener) listeners.get(i)).onRead(request);

    }

    /**
     * ���ͻ��˿��Կ�ʼ���շ���˷�������ʱ�������¼���
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
     * �ͻ��˿��Կ�ʼ���շ���˷�������ʱ�������¼���
     * 
     * @param request
     * @throws Exception
     */
    public void fireOnClosed(Request request) throws Exception {
        for (int i = listeners.size() - 1; i >= 0; i--)
            ((ServerListener) listeners.get(i)).onClosed(request);
    }

    /**
     * ���ͻ���������������ӿ�ʼ�����Ͽ������ڼ䷢������ʱ�������¼���
     * 
     * @param error
     */
    public void fireOnError(String error) {
        for (int i = listeners.size() - 1; i >= 0; i--)
            ((ServerListener) listeners.get(i)).onError(error);
    }
}
