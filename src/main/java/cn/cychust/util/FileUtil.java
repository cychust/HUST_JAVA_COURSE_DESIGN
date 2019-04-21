package cn.cychust.util;

import java.io.*;

/**
 * @program: HUST_JAVA_COURSE_DESIGN
 * @description:
 * @author: Yichao Chen
 * @create: 2019-04-21 22:56
 **/
public class FileUtil {
    private final String FILE_ROOT_PATH = "";

    public boolean isExists(String username) {
        File file = new File(FILE_ROOT_PATH + username);
        return file.exists();
    }

    public void savePassword(String username, String password) {
        File file = new File(FILE_ROOT_PATH + username);
        OutputStreamWriter writer = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            writer = new OutputStreamWriter(new FileOutputStream(file));
            writer.write(password);
//            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }

}
