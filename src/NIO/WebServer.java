package NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @program: learning
 * @author: baichen
 * socket服务端，先启动服务端
 * 1.通过ServerSocketChannel 绑定ip地址和端口号，与客户端的一致
 * <p>
 * 2.通过ServerSocketChannelImpl的accept()方法创建一个SocketChannel对象用户从客户端读/写数据
 * <p>
 * 3.创建读数据/写数据缓冲区对象来读取客户端数据或向客户端发送数据
 * <p>
 * 4. 关闭SocketChannel和ServerSocketChannel
 **/
public class WebServer {
    public static void main(String args[]) throws IOException {
        try {
            // 1.通过ServerSocketChannel的open()方法创建一个ServerSocketChannel对象，open方法的作用：打开套接字通道
            ServerSocketChannel ssc = ServerSocketChannel.open();
            // 2.通过ServerSocketChannel绑定ip地址和port(端口号)
            ssc.socket().bind(new InetSocketAddress("127.0.0.1", 3333));
            // 通过ServerSocketChannelImpl的accept()方法创建一个SocketChannel对象用户从客户端读/写数据
            SocketChannel socketChannel = ssc.accept();
            // 3.创建写数据的缓存区对象
            ByteBuffer writeBuffer = ByteBuffer.allocate(128);
            writeBuffer.put("hello WebClient this is from WebServer".getBytes());
            writeBuffer.flip();
            socketChannel.write(writeBuffer);
            // 创建读数据的缓存区对象
            ByteBuffer readBuffer = ByteBuffer.allocate(128);
            // 读取缓存区数据
            socketChannel.read(readBuffer);
            StringBuilder stringBuffer = new StringBuilder();
            // 4.将Buffer从写模式变为可读模式
            readBuffer.flip();
            while (readBuffer.hasRemaining()) {
                stringBuffer.append((char) readBuffer.get());
            }
            System.out.println("从客户端接收到的数据：" + stringBuffer);
            socketChannel.close();
            ssc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
