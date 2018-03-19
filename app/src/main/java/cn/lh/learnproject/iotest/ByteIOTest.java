package cn.lh.learnproject.iotest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by liaohui on 2018/3/19.
 */

public class ByteIOTest {
    public static void main(String[] args) {
        copyFile("F://testimg.png", "F://ttt/test.png");
        copyFile("F://test.txt", "F://ttt/test.png");

    }

    private static void copyFile(String srcName, String destName) {
        FileOutputStream fileOutputStream = null;
        FileInputStream fileInputStream = null;
        try {
            File src = new File(srcName);
            fileInputStream = new FileInputStream(src);

            File file = new File(destName);
            if (!file.exists()) {
                file.createNewFile();
            }
            fileOutputStream = new FileOutputStream(file, true);
            byte[] buf = new byte[10];
            int len = -1;
            while ((len = fileInputStream.read(buf)) != -1) {
                fileOutputStream.write(buf, 0, len);
            }
            fileOutputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null) fileOutputStream.close();
                if (fileInputStream != null) fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
