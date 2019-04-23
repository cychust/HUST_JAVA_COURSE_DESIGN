package cn.cychust.data.tsrxx_item;

import cn.cychust.data.tghxx_item.GHXX_Item;
import cn.cychust.mysql.C3p0helper;
import cn.cychust.mysql.DatabaseManager;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @program: HUST_JAVA_COURSE_DESIGN
 * @description:
 * @author: Yichao Chen
 * @create: 2019-04-23 21:13
 **/
public class Dao {

    private final static String FIND_ALL_BY_YSBH =
            "SELECT KSMC, T_GHXX.YSBH, YSMC, T_HZXX.SFZJ, COUNT(*), SUM(T_GHXX.GHFY)" +
                    "FROM T_GHXX, T_KSYS, T_KSXX, T_HZXX" +
                    "WHERE T_GHXX.YSBH = T_KSYS.YSBH AND T_GHXX.HZBH = T_HZXX.HZBH AND T_HZXX.KSBH = T_KSXX.KSBH AND" +
                    "T_GHXX.RQSJ BETWEEN ? and ?" +
                    "GROUP BY T_GHXX.YSBH, SFZJ";


    public static Optional findAllByYSBH(String ysbh) {
        List<GHXX_Item> list = new ArrayList<>();
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseManager.getINSTANCE().getConnection();
            statement = connection.prepareStatement(FIND_ALL_BY_YSBH);
            statement.setString(1, ysbh);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                GHXX_Item item = new GHXX_Item();
                item.setGHBH(resultSet.getString("GHBH"));
                item.setBRMC(resultSet.getString("BRMC"));
                item.setRQSJ(resultSet.getString("RQSJ"));
                item.setHZLB(resultSet.getString("HZLB"));
                list.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } finally {
            C3p0helper.attemptClose(resultSet);
            C3p0helper.attemptClose(statement);
            C3p0helper.attemptClose(connection);
        }
        return Optional.ofNullable(list);
    }
}
