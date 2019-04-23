package cn.cychust.data.thzxx.source.dao;

import cn.cychust.data.thzxx.T_HZXX;
import cn.cychust.data.tksxx.T_KSXX;
import cn.cychust.mysql.C3p0helper;
import cn.cychust.mysql.DatabaseManager;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * @program: HUST_JAVA_COURSE_DESIGN
 * @description:
 * @author: Yichao Chen
 * @create: 2019-04-22 21:30
 **/
public class HzxxDao {

    private final static String FIND_ONE_BY_ID = "SELECT * FROM T_HZXX WHERE KSBH=?";


    public static Optional findOneByHZBH(String id) {

        T_HZXX t_hzxx = null;
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseManager.getINSTANCE().getConnection();
            statement = connection.prepareStatement(FIND_ONE_BY_ID);
            statement.setString(1, id);
            resultSet = statement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                t_hzxx = new T_HZXX();
                t_hzxx.setHZBH(resultSet.getString(1));
                t_hzxx.setHZMC(resultSet.getString(2));
                t_hzxx.setPYZS(resultSet.getString(3));
                t_hzxx.setKSBH(resultSet.getString(4));
                t_hzxx.setSFZJ(resultSet.getBoolean(5));
                t_hzxx.setGHRS(resultSet.getInt(6));
                t_hzxx.setGHFY(resultSet.getFloat(7));
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
        return Optional.ofNullable(t_hzxx);
    }
}
