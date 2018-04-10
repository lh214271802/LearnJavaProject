package cn.lh.learnproject;

import java.io.Closeable;
import java.io.IOException;

public class CloseUtils {

    public static void close(Closeable... cloneables) {
        for (Closeable cloneable : cloneables) {
            if (cloneable != null) {
                try {
                    cloneable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
