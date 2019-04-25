package cn.cychust.data.tksxx.source.local;

import cn.cychust.data.tbrxx.T_BRXX;
import cn.cychust.comm.Dao;
import cn.cychust.data.tbrxx.source.local.dao.UserDao;
import cn.cychust.data.tksxx.T_KSXX;
import cn.cychust.data.tksys.T_KSYS;
import cn.cychust.mysql.C3p0helper;
import cn.cychust.mysql.DatabaseManager;
import org.apache.log4j.Logger;

import java.beans.PropertyVetoException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @program: HUST_JAVA_COURSE_DESIGN
 * @description:
 * @author: Yichao Chen
 * @create: 2019-04-21 23:23
 **/
public class KsxxDao extends Dao {


    private static final Logger LOGGER = Logger.getLogger(UserDao.class);

    private final static String ADD_ONE_STATEMENT = "INSERT INTO T_KSXX (KSBH,KSMC,PYZS) VALUES(?,?,?)";
    private final static String FIND_ONE_BY_ID = "SELECT * FROM T_KSXX WHERE KSBH=?";
    private final static String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS T_KSXX(" +
                    "KSBH CHAR(6) not NULL, " +
                    "KSMC CHAR(10) not NULL, " +
                    "PYZS CHAR(8) not NULL, " +
                    "PRIMARY KEY ( KSBH )" +
                    ")ENGINE=InnoDB DEFAULT CHARSET=utf8;";

    private final static String FIND_ALL = "SELECT * FROM T_KSXX";

    private final static String UPDATE_ONE_BY_ID = "UPDATE T_KSXX SET KSMC=?, PYZS=? WHERE KSBH=?";


    private final static String FIND_ALL_BY_PYZS = "SELECT * FROM T_KSXX WHERE PYZS LIKE ?";


    /**
     * 创建表
     *
     * @return
     */
    public static Optional<Boolean> createTable() {
        return Dao.createTable(CREATE_TABLE);
    }
//
//    public static ResultSet findAllUser() {
//        return Dao.findAll(FIND_ALL);
//    }

    /**
     * @Description: 根据id查找
     * @Param: [id]
     * @return: java.util.Optional<cn.cychust.data.tbrxx.T_BRXX>
     * @Author: Yichao Chen
     * @Date: 19-4-21
     * @Time: 下午11:33
     **/
    public static Optional<T_KSXX> findOne(String id) {
        T_KSXX tKsxx = null;
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseManager.getINSTANCE().getConnection();
            statement = connection.prepareStatement(FIND_ONE_BY_ID);
            statement.setString(1, id);
            resultSet = statement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                tKsxx = new T_KSXX();
                tKsxx.setKSBH(resultSet.getString(1));
                tKsxx.setKSMC(resultSet.getString(2));
                tKsxx.setPYZC(resultSet.getString(3));
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
        return Optional.ofNullable(tKsxx);
    }

    /**
     * 返回全部的T_BRXX
     *
     * @return
     */
    public static Optional<List<T_KSXX>> findAll() {
        List<T_KSXX> list = new ArrayList<>();
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = DatabaseManager.getINSTANCE().getConnection();
            resultSet = connection.createStatement().executeQuery(FIND_ALL);
            while (resultSet.next()) {
                T_KSXX tKsxx = new T_KSXX();
                tKsxx.setKSBH(resultSet.getString(1));
                tKsxx.setKSMC(resultSet.getString(2));
                tKsxx.setPYZC(resultSet.getString(3));
                list.add(tKsxx);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } finally {
            C3p0helper.attemptClose(resultSet);
            C3p0helper.attemptClose(connection);
        }
        return Optional.ofNullable(list);
    }

    public static Optional findAllByPYZS(String pyzs) {
        List<T_KSXX> list = new ArrayList<>();
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseManager.getINSTANCE().getConnection();
            statement = connection.prepareStatement(FIND_ALL_BY_PYZS);
            statement.setString(1, "%" + pyzs + "%");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                T_KSXX tKsxx = new T_KSXX();
                tKsxx.setKSBH(resultSet.getString(1));
                tKsxx.setKSMC(resultSet.getString(2));
                tKsxx.setPYZC(resultSet.getString(3));
                list.add(tKsxx);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } finally {
            C3p0helper.attemptClose(resultSet);
            C3p0helper.attemptClose(connection);
        }
        return Optional.ofNullable(list);
    }

}
