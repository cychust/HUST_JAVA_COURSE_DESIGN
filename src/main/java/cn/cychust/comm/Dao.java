package cn.cychust.comm;

import cn.cychust.mysql.C3p0helper;
import cn.cychust.mysql.DatabaseManager;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

/**
 * @program: HUST_JAVA_COURSE_DESIGN
 * @description: 公共方法
 * @author: Yichao Chen
 * @create: 2019-04-21 23:35
 **/
public class Dao{

    public static Optional<Boolean> createTable(String createStatement) {
        boolean result = true;
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DatabaseManager.getINSTANCE().getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(createStatement);
        } catch (SQLException e) {
            result = false;
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            result = false;
            e.printStackTrace();
        } finally {
            C3p0helper.attemptClose(statement);
            C3p0helper.attemptClose(connection);
        }
        return Optional.of(result);
    }
//    public static ResultSet findAll(String findAllStatement){
//        ResultSet resultSet = null;
//        Connection connection = null;
//        try {
//            connection = DatabaseManager.getINSTANCE().getConnection();
//            resultSet = connection.createStatement().executeQuery(findAllStatement);
//            while (resultSet.next()) {
////                LOGGER.info(resultSet.getObject(1));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (PropertyVetoException e) {
//            e.printStackTrace();
//        } finally {
//            C3p0helper.attemptClose(resultSet);
//            C3p0helper.attemptClose(connection);
//        }
//        return resultSet;
//    }
}
