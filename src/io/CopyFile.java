package io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @program: learning
 * @author: baichen
 * 文件复制
 * 1.先创建输入流和输出流
 * 2.创建缓冲区buffer(byte数组)
 * 3.遍历输入流通过read方法读取缓存区的数据，输出流通过write方法写入
 * 4.关闭输入流和输出流
 **/
public class CopyFile {
    public static void copyFile(String src, String dist) throws IOException {
        //要先读取源文件，所以要先new输入流类
        FileInputStream in=new FileInputStream(src);
        FileOutputStream out=new FileOutputStream(dist);
        byte[] buffer=new byte[20*1024];
        int cnt;
        // read() 最多读取 buffer.length 个字节
        // 返回的是实际读取的个数
        // 返回 -1 的时候表示读到 eof，即文件尾
        // read 从该输入流读取最多 len字节的数据为字节数组。
            //相关参数：b - 读取数据的缓冲区。
                     //off - 目标数组 b的起始偏移量
                     //len - 读取的最大字节数。
        while ((cnt=in.read(buffer,0,buffer.length))!=0){
            out.write(buffer,0,cnt);
        }
        in.close();
        out.close();
    }
}
