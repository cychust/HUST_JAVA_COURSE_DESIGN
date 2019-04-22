package cn.cychust.data.tksys.source.local;

import cn.cychust.comm.Dao;
import cn.cychust.data.tbrxx.T_BRXX;
import cn.cychust.data.tksxx.T_KSXX;
import cn.cychust.data.tksys.T_KSYS;
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
 * @create: 2019-04-22 11:28
 **/
public class KsysDao extends Dao {

    private final static String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS T_KSYS(" +
                    "YSBH CHAR(6) not NULL, " +
                    "KSBH CHAR(6) not NULL, " +
                    "YSMC CHAR(10) not NULL, " +
                    "PYZS CHAR(4) not NULL, " +
                    "DLKL CHAR(8) not NULL, " +
                    "SFZJ BOOL not NULL, " +
                    "DLRQ DateTime , " +
                    "PRIMARY KEY ( YSBH )" +
                    ")ENGINE=InnoDB DEFAULT CHARSET=utf8;";
    private final static String FIND_ALL = "";
    private final static String FIND_ONE_BY_USER_AND_PASS = "SELECT * FROM T_KSYS WHERE YSBH = ? AND DLKL = ?";

    private final static String FIND_ONE_BY_USER = "SELECT * FROM T_KSYS WHERE YSBH = ?";

    private final static String ADD_ONE_STATEMENT = "INSERT INTO T_KSYS (YSBH,KSBH,YSMC,PYZS,DLKL,SFZJ,DLRQ) VALUES(?,?,?,?,?,?,?)";

    private final static String FIND_ALL_BY_KSBH = "SELECT * FROM T_KSYS WHERE KSBH = ?";

    public static Optional<Boolean> createTable() {
        return createTable(CREATE_TABLE);
    }

    public static Optional<T_KSYS> findOne(String user, String pass) {
        T_KSYS tKsys = null;
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseManager.getINSTANCE().getConnection();
            statement = connection.prepareStatement(FIND_ONE_BY_USER_AND_PASS);
            statement.setString(1, user);
            statement.setString(2, pass);
            resultSet = statement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                tKsys = new T_KSYS();
                tKsys.setYSBH(resultSet.getString(1));
                tKsys.setKSBH(resultSet.getString(2));
                tKsys.setYSMC(resultSet.getString(3));
                tKsys.setPYZS(resultSet.getString(4));
                tKsys.setDLKL(resultSet.getString(5));
                tKsys.setSFZJ(resultSet.getBoolean(6));
                tKsys.setDLRQ(resultSet.getTimestamp(7));
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
        return Optional.ofNullable(tKsys);
    }

    public static Optional<T_KSYS> findOne(String usr) {
        T_KSYS tKsys = null;
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseManager.getINSTANCE().getConnection();
            statement = connection.prepareStatement(FIND_ONE_BY_USER);
            statement.setString(1, usr);
            resultSet = statement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                tKsys = new T_KSYS();
                tKsys.setYSBH(resultSet.getString(1));
                tKsys.setKSBH(resultSet.getString(2));
                tKsys.setYSMC(resultSet.getString(3));
                tKsys.setPYZS(resultSet.getString(4));
                tKsys.setDLKL(resultSet.getString(5));
                tKsys.setSFZJ(resultSet.getBoolean(6));
                tKsys.setDLRQ(resultSet.getTimestamp(7));
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
        return Optional.ofNullable(tKsys);
    }


    public static Optional findAllByKSBH(String ksbh) {
        List<T_KSYS> list = new ArrayList<>();
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseManager.getINSTANCE().getConnection();
            statement = connection.prepareStatement(FIND_ALL_BY_KSBH);
            statement.setString(1, ksbh);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                T_KSYS tKsys = new T_KSYS();
                tKsys.setYSBH(resultSet.getString(1));
                tKsys.setKSBH(resultSet.getString(2));
                tKsys.setYSMC(resultSet.getString(3));
                tKsys.setPYZS(resultSet.getString(4));
                tKsys.setDLKL(resultSet.getString(5));
                tKsys.setSFZJ(resultSet.getBoolean(6));
                tKsys.setDLRQ(resultSet.getTimestamp(7));
                list.add(tKsys);
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

    public static Optional<T_KSYS> saveOne(T_KSYS t_ksys) {
        if (t_ksys == null) {
            throw new NullPointerException("t_brxx can not be null");
        }
        T_KSYS result = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseManager.getINSTANCE().getConnection();
            statement = connection.prepareStatement(ADD_ONE_STATEMENT);
            statement.setString(1, t_ksys.getYSBH());
            statement.setString(2, t_ksys.getKSBH());
            statement.setString(3, t_ksys.getYSMC());
            statement.setString(4, t_ksys.getPYZS());
            statement.setString(5, t_ksys.getDLKL());
            statement.setBoolean(6, t_ksys.isSFZJ());
            statement.setTimestamp(7, t_ksys.getDLRQ());
            statement.execute();
            result = t_ksys;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } finally {
            C3p0helper.attemptClose(statement);
            C3p0helper.attemptClose(connection);
        }
        return Optional.of(result);
    }
}
