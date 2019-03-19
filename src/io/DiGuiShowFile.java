package io;

import java.io.File;

/**
 * @program: learning
 * @author: baichen
 * 递归显示文件夹下所有的文件
 **/
public class DiGuiShowFile {
    public static void listAllFiles(File dir) {
        if (dir == null || !dir.exists()) {
            return;
        }
        if (dir.isFile()) {
            System.out.println(dir.getName());
            return;
        }
//        listFiles返回一个抽象路径名数组，表示由该抽象路径名表示的目录中的文件。
        for (File file : dir.listFiles()) {
            listAllFiles(file);
        }
    }
}
