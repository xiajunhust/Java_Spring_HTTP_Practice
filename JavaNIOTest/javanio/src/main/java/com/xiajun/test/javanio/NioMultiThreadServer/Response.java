package com.xiajun.test.javanio.NioMultiThreadServer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * <p>Title: 回应器</p>
 * <p>Description: 用于向客户端发送数据</p>
 * @author starboy
 * @version 1.0
 */

public class Response {
    private final SocketChannel sc;

    public Response(SocketChannel sc) {
        this.sc = sc;
    }

    /**
     * 向客户端写数据
     * @param data byte[]　待回应数据
     */
    public void send(byte[] data) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(data.length);
        buffer.put(data, 0, data.length);
        buffer.flip();
        sc.write(buffer);
    }
}
