package file;

import java.io.*;

/**
 * @program: learning
 * @author: baichen
 * 测试缓冲流
 **/
public class BufferedStreamTest {
    public  static void main(String[] args)throws IOException {
        // 创建文件输入输出流和缓冲输入输出流
        FileInputStream fis=null;
        FileOutputStream fos=null;
        BufferedInputStream bis=null;
        BufferedOutputStream bos=null;
        try {
            //创建字节输入流
            fis=new FileInputStream("/Users/jack/Desktop/IdeaProject/learning/src/file/Test.txt");
            //创建字节输出流
            fos=new FileOutputStream("/Users/jack/Desktop/IdeaProject/learning/src/file/bufferTest.txt");
            //创建字节缓存输入流
            bis=new BufferedInputStream(fis);
            //创建字节缓存输出流
            bos=new BufferedOutputStream(fos);

            byte[] b=new byte[1024];
            int hasRead=0;
            //循环从缓存流中读取数据
            while((hasRead=bis.read(b))>0){
                //向缓存流中写入数据，读取多少写入多少
                bos.write(b,0,hasRead);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            bis.close();
            bos.close();
        }
    }
}
