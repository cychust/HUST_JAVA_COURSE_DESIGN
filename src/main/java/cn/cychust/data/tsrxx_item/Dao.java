package cn.cychust.data.tsrxx_item;

import cn.cychust.data.tghxx_item.GHXX_Item;
import cn.cychust.mysql.C3p0helper;
import cn.cychust.mysql.DatabaseManager;

import java.beans.PropertyVetoException;
import java.sql.*;
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

    private final static String FIND_ALL_BETWEEN =
            "SELECT KSMC, T_GHXX.YSBH, YSMC, T_HZXX.SFZJ, COUNT(*), SUM(T_GHXX.GHFY) " +
                    "FROM T_GHXX, T_KSYS, T_KSXX, T_HZXX " +
                    "WHERE T_GHXX.YSBH = T_KSYS.YSBH AND T_GHXX.HZBH = T_HZXX.HZBH AND T_HZXX.KSBH = T_KSXX.KSBH AND " +
                    "T_GHXX.RQSJ BETWEEN ? and ? " +
                    "GROUP BY T_GHXX.YSBH, SFZJ, KSMC";


    private final static String FIND_ALL =
            "SELECT KSMC, T_GHXX.YSBH, YSMC, T_HZXX.SFZJ, COUNT(*), SUM(T_GHXX.GHFY) " +
                    "FROM T_GHXX, T_KSYS, T_KSXX, T_HZXX " +
                    "WHERE T_GHXX.YSBH = T_KSYS.YSBH AND T_GHXX.HZBH = T_HZXX.HZBH AND T_HZXX.KSBH = T_KSXX.KSBH " +
                    "GROUP BY T_GHXX.YSBH, T_HZXX.SFZJ, KSMC";


    public static Optional findAllBetween(Timestamp start, Timestamp end) {
        List<SRXX_Item> list = new ArrayList<>();
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseManager.getINSTANCE().getConnection();
            statement = connection.prepareStatement(FIND_ALL_BETWEEN);
            statement.setTimestamp(1, start);
            statement.setTimestamp(2, end);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                SRXX_Item item = new SRXX_Item();
                item.setKSMC(resultSet.getString("KSMC"));
                item.setYSBH(resultSet.getString("YSBH"));
                item.setYSMC(resultSet.getString("YSMC"));
                item.setSFZJ(resultSet.getBoolean(4));
                item.setGHRC(resultSet.getInt(5));
                item.setSRHJ(resultSet.getFloat(6));
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

    public static Optional findAll() {
        List<SRXX_Item> list = new ArrayList<>();
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseManager.getINSTANCE().getConnection();
            statement = connection.prepareStatement(FIND_ALL);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                SRXX_Item item = new SRXX_Item();
                item.setKSMC(resultSet.getString("KSMC"));
                item.setYSBH(resultSet.getString("YSBH"));
                item.setYSMC(resultSet.getString("YSMC"));
                item.setSFZJ(resultSet.getBoolean(4));
                item.setGHRC(resultSet.getInt(5));
                item.setSRHJ(resultSet.getFloat(6));
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
