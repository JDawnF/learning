package file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @program: learning
 * @author: baichen
 * 测试文件输出流
 * 通过定义了两个继承了输入流InputStream和输出流OutputStream的类：
 * FileInputStream和FileOutputStream，然后通过读取文件Test.txt，
 * 此时程序应该用输入流FileInputStream的read方法，然后将读取到数据存到newTest.txt，
 * 用FileOutputStream的write方法，注意最后两个流都要用close关闭
 **/
public class FileOutputStreamTest {
    public  static void main(String[] args)throws IOException {
        FileInputStream fis=null;
        FileOutputStream fos=null;
        try {
            //创建字节输入流，文件不存在会报错
            fis=new FileInputStream("/Users/jack/Desktop/IdeaProject/learning/src/file/Test.txt");
            //创建字节输出流,如果文件不存在会创建，如果是跟输入流的文件一样的话，会清空
            fos=new FileOutputStream("/Users/jack/Desktop/IdeaProject/learning/src/file/newTest.txt");
            // 创建一个字节数组
            byte[] b=new byte[1024];
            int hasRead=0;
            //循环从输入流中取出数据
            while((hasRead=fis.read(b))>0){
                //每读取一次，即写入文件输入流，读了多少，就写多少。
                fos.write(b,0,hasRead);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            fis.close();
            fos.close();
        }
    }
}
