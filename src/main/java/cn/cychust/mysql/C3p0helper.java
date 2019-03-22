package cn.cychust.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @program: hospital-manager-system
 * @description:
 * @author: Yichao Chen
 * @create: 2019-03-22 17:41
 **/
public class C3p0helper {

    public static void attemptClose(ResultSet o) {
        try {
            if (o != null) o.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void attemptClose(Statement o) {
        try {
            if (o != null) o.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void attemptClose(Connection o) {
        try {
            if (o != null) o.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
