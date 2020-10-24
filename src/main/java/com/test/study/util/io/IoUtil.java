package com.test.study.util.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author lenovo2
 */
public class IoUtil {


    // 根据系统自动修改斜杆和反斜杠
    public static String RepalceSeparator(String s) {
        return s.replace("\\", File.separator).replace("/", File.separator);
    }

    public static void test() {
        InputStream in = System.in;
        InputStreamReader reader = new InputStreamReader(in);

        BufferedReader bReader = new BufferedReader(reader);
        try {
            System.out.println(bReader.read());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFile(String path) throws IOException {
        path = RepalceSeparator(path);
        File file = new File(path);
        StringBuilder stringBuffer = new StringBuilder();
        try (FileReader fReader = new FileReader(file)) {
            char[] buf = new char[1024 * 10];
            int temp = 0;
            while ((temp = fReader.read(buf)) > 0) {
                stringBuffer.append(new String(buf, 0, temp));
            }
            return stringBuffer.toString();
        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        }
    }

    public static void writeFile(String path, String s) throws IOException {
        path = RepalceSeparator(path);
        File file = new File(path);
        if (!file.exists()) {
            creatFile(path);
        }
        try (FileWriter fileWriter = new FileWriter(file, true)) {
            fileWriter.write(s);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 通过io流来复制文件
     *
     * @param FromPath 源路径+文件名
     * @param ToPath   新路径（没有文件名）
     * @param fileName 新文件名
     * @throws IOException
     */
    public static void CopyFile(String FromPath, String ToPath, String fileName) throws IOException {
        FromPath = RepalceSeparator(FromPath);
        File file = new File(RepalceSeparator(ToPath));
        if (!file.exists()) {
            creatFolder(ToPath);
        }
        ToPath = RepalceSeparator(ToPath + "\\" + fileName);
        byte[] buf = new byte[1024 * 10];
        try (FileInputStream fInputStream = new FileInputStream(FromPath);
             FileOutputStream fOutputStream = new FileOutputStream(ToPath)) {
            int length = 0;
            while ((length = fInputStream.read(buf)) > 0) {
                fOutputStream.write(buf, 0, length);
            }
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * @param FromPath 源路径+文件名
     * @param ToPath   新路径（没有文件名）,新文件名与旧文件名相同
     * @throws IOException
     */
    public static void CopyFile(String FromPath, String ToPath) throws IOException {
        FromPath = RepalceSeparator(FromPath);
        File file = new File(FromPath);
        CopyFile(FromPath, ToPath, file.getName());
    }

    /**
     * 通过改变文件路径来移动文件
     * 如果文件夹不存在，自动创建
     *
     * @param fromPath 源路径+文件名
     * @param toPath   新路径（没有文件名）,新文件名与旧文件名相同
     * @throws Exception
     */

    public static boolean changeFilePath(String fromPath, String toPath) {

        toPath = RepalceSeparator(toPath);
        File fromfile = new File(RepalceSeparator(fromPath));
        File fileToPath = new File(toPath);
        if (!fileToPath.exists()) {
            creatFolder(toPath);
        }
        File tofile = new File(toPath + File.separator + fromfile.getName());
        return fromfile.renameTo(tofile);

    }

    public static boolean creatFolder(String folderPath) {

        File filePath = new File(RepalceSeparator(folderPath));
        if (!filePath.exists()) {
            return filePath.mkdirs();
        } else {
            return true;
        }
    }


    public static boolean creatFileOnly(String filePath) throws IOException {

        File fileName = new File(RepalceSeparator(filePath));
        if (!fileName.exists()) {
            return fileName.createNewFile();
        } else {
            return true;
        }
    }


    public static boolean delFile(String filePath) {

        File file = new File(RepalceSeparator(filePath));
        if (file.delete()) {
            return true;
        } else {
            return false;
        }
    }

    public static void creatFile(String filePath) throws IOException {
        filePath = RepalceSeparator(filePath);
        if (filePath.lastIndexOf(File.separator) < 0) {
            throw new FileNotFoundException("文件路径不正确");
        }
        String FolderPath = filePath.substring(0, filePath.lastIndexOf(File.separator));
        File Folder = new File(FolderPath);
        if (Folder.exists()) {
            creatFileOnly(filePath);
        } else {
            creatFolder(FolderPath);
            creatFileOnly(filePath);
        }
    }

}
