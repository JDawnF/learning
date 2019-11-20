package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @Author baichen
 * @Date 2019/11/4
 * @Description
 */
public class FileUtils {
    public static void main(String[] args) throws IOException {
//        renameFiles("E:\\books\\31-何帆读书俱乐部\\",".mp3");
        moveFiles("E:\\books\\31-何帆读书俱乐部\\", "E:\\books\\31-何帆读书俱乐部\\mp3\\", ".mp3");
        moveFiles("E:\\books\\31-何帆读书俱乐部\\", "E:\\books\\31-何帆读书俱乐部\\pic\\", ".png");
//        delFiles("", "png");
//        delDirs("E:\\books\\香帅的北大金融课\\xsJRK\\");
    }

    private static void moveFiles(String oriPath, String targetPath, String endName) {
        File oriFile = new File(oriPath);
        File[] files = oriFile.listFiles();
        // 遍历文件
        for (File file : files) {
            if (file.isDirectory()) {
                moveFiles(file.toString(), targetPath, endName);
            } else {
                if (file.getName().endsWith(endName)) {
                    file.renameTo(new File(targetPath + file.getName()));
                }
            }
        }
    }

    private static void renameFiles(String oriPath, String endName) {
        File oriFile = new File(oriPath);
        File[] files = oriFile.listFiles();
        // 遍历文件
        for (File file : files) {
            if (file.isDirectory()) {
                renameFiles(file.toString(), endName);
            } else {
                if (file.getName().endsWith(endName)) {
                    file.renameTo(new File(oriPath + file.getName().replace("-www.nshufang.com南书房在线收听", "")));
                }
            }
        }
    }

    private static void delFiles(String oriPath, String endName) {
        File oriFile = new File(oriPath);
        File[] files = oriFile.listFiles();
        // 遍历文件
        for (File file : files) {
            if (file.isDirectory()) {
                delFiles(file.toString(), endName);
            } else {
                if (file.getName().endsWith(endName)) {
                    file.delete();
                }
            }
        }
    }

    private static void delDirs(String oriPath) {
        File oriFile = new File(oriPath);
        File[] files = oriFile.listFiles();
        // 遍历文件
        for (File file : files) {
            if (file.isDirectory() && files.length > 0) {
                if (file.listFiles().length > 0) {
                    delDirs(file.toString());
                } else {
                    file.delete();
                }
            } else {
                file.delete();
            }
        }
    }

    private static void getFile(String path, String endName) throws IOException {
        Path start = FileSystems.getDefault().getPath(path);
        Files.walk(start).filter(childpath -> childpath.toFile().isFile())
                .filter(oriPath -> oriPath.toString().endsWith(endName))
                .forEach(System.out::println);
    }
}
