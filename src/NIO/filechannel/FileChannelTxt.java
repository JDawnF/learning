package NIO.filechannel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @program: learning
 * @author: baichen
 * 使用FileChannel读取数据到Buffer（缓冲区）以及利用Buffer（缓冲区）写入数据到FileChannel
 **/
public class FileChannelTxt{
    public static void main(String args[]) throws IOException {
        // 1.创建一个RandomAccessFile（随机访问文件）对象，
        RandomAccessFile raf = new RandomAccessFile("/Users/jack/Desktop/IdeaProject/learning/src/NIO/filechannel/fileTest.txt","rw");
        // 通过RandomAccessFile对象的getChannel()方法，获取FileChannel
        FileChannel inChannel = raf.getChannel();
        // 2.创建一个读数据缓冲区对象，allocate表示分配非直接缓冲区，建立在JVM的内存
        ByteBuffer buf = ByteBuffer.allocate(48);
        // 3.从通道中读取缓冲区的字节数据
        int bytesRead = inChannel.read(buf);
        // 创建一个字节写数据缓冲区对象
        ByteBuffer buf2 = ByteBuffer.allocate(48);
        // 写入数据
        buf2.put("filechannel test".getBytes());

        //flip()方法可以把Buffer从写模式切换到读模式。调用flip方法会把position归零，
        // 并设置limit为之前的position的值。 也就是说，现在position代表的是读取位置，
        // limit标示的是已写入的数据位置，将读写的一些东西互换
        buf2.flip();
        inChannel.write(buf);
        //从同道中读取的数据不为1的时候
        while(bytesRead !=-1){
            System.out.println("Read "+ bytesRead);
            // Buffer有两种模式，写模式和读模式。在写模式下调用flip()之后，Buffer从写模式变成读模式。
            buf.flip();
            // 如果还有未读内容
            while(buf.hasRemaining()){
                System.out.print((char) buf.get());
            }
            // 清空缓存区
            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        // 关闭RandomAccessFile（随机访问文件）对象
        raf.close();
    }
}
