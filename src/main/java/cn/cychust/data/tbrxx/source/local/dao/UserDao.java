package cn.cychust.data.tbrxx.source.local.dao;

import cn.cychust.comm.Dao;
import cn.cychust.data.tbrxx.T_BRXX;
import cn.cychust.mysql.C3p0helper;
import cn.cychust.mysql.DatabaseManager;
import org.apache.log4j.Logger;

import java.beans.PropertyVetoException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @program: hospital-manager-system
 * @description:
 * @author: Yichao Chen
 * @create: 2019-03-22 16:26
 **/
public class UserDao {


    private static final Logger LOGGER = Logger.getLogger(UserDao.class);

    private final static String ADD_ONE_STATEMENT = "INSERT INTO T_BRXX (BRBH,BRMC,DLKL,YCJE,DLRQ) VALUES(?,?,?,?,?)";
    private final static String FIND_ONE_BY_USER_AND_PASS = "SELECT * FROM T_BRXX WHERE BRBH = ? AND DLKL = ?";
    private final static String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS T_BRXX(" +
                    "BRBH CHAR(6) not NULL, " +
                    "BRMC CHAR(10) not NULL, " +
                    "DLKL CHAR(8) not NULL, " +
                    "YCJE DECIMAL(10,2) not NULL, " +
                    "DLRQ DateTime not NULL, " +
                    "PRIMARY KEY ( BRBH )" +
                    ")ENGINE=InnoDB DEFAULT CHARSET=utf8;";

    private final static String FIND_ALL = "SELECT * FROM T_BRXX";

    private final static String UPDATE_ONE_BY_ID = "UPDATE T_BRXX SET BRMC=?, DLKL=?, YCJE=?, DLRQ=? WHERE BRBH=?";

    /**
     * 创建表
     *
     * @return
     */
    public static Optional<Boolean> createTable() {
        return Dao.createTable(CREATE_TABLE);
    }

//    public static ResultSet findAllUser() {
//        return Dao.findAll(FIND_ALL);
//    }

    /**
     * 根据 user pass 查找
     *
     * @param user
     * @param pass
     * @return
     */
    public static Optional<T_BRXX> findOne(String user, String pass) {
        T_BRXX tBrxx = null;
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
                tBrxx = new T_BRXX();
                tBrxx.setBRBH(resultSet.getString(1));
                tBrxx.setBRMC(resultSet.getString(2));
                tBrxx.setDLKL(resultSet.getString(3));
                tBrxx.setDLRQ(resultSet.getTimestamp(5));
                tBrxx.setYCJE(resultSet.getFloat(4));
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
        return Optional.ofNullable(tBrxx);
    }

    /**
     * 返回全部的T_BRXX
     *
     * @return
     */
    public static Optional<List<T_BRXX>> findAll() {
        List<T_BRXX> list = new ArrayList<>();
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = DatabaseManager.getINSTANCE().getConnection();
            resultSet = connection.createStatement().executeQuery("select  * from  t_brxx");
            while (resultSet.next()) {
                T_BRXX tBrxx = new T_BRXX();
                tBrxx.setBRBH(resultSet.getString(0));
                tBrxx.setBRMC(resultSet.getString(1));
                tBrxx.setDLKL(resultSet.getString(2));
                tBrxx.setDLRQ(resultSet.getTimestamp(3));
                tBrxx.setYCJE(resultSet.getFloat(4));
                list.add(tBrxx);
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

    /**
     * 添加一个 T_BRXX
     *
     * @param t_brxx
     * @return
     * @throws NullPointerException
     */
    public static Optional<T_BRXX> addOne(T_BRXX t_brxx) throws NullPointerException {
        if (t_brxx == null) {
            throw new NullPointerException("t_brxx can not be null");
        }
        T_BRXX result = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseManager.getINSTANCE().getConnection();
            statement = connection.prepareStatement(ADD_ONE_STATEMENT);
            statement.setString(1, t_brxx.getBRBH());
            statement.setString(2, t_brxx.getBRMC());
            statement.setString(3, t_brxx.getDLKL());
            statement.setFloat(4, t_brxx.getYCJE());
            statement.setTimestamp(5, t_brxx.getDLRQ());
            statement.execute();
            result = t_brxx;
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


    /**
     * 添加多个 T_BRXX
     *
     * @param tBrxxes
     * @return
     * @throws IllegalArgumentException
     */
    public static Optional<List<T_BRXX>> addAll(List<T_BRXX> tBrxxes) throws IllegalArgumentException {
        if (tBrxxes.size() == 0) throw new IllegalArgumentException("tBrxxes can not empty");
        PreparedStatement statement = null;
        Connection connection = null;
        boolean result = true;
        try {
            connection = DatabaseManager.getINSTANCE().getConnection();
            for (T_BRXX t_brxx : tBrxxes) {
                statement = connection.prepareStatement(ADD_ONE_STATEMENT);
                statement.setString(1, t_brxx.getBRBH());
                statement.setString(2, t_brxx.getBRMC());
                statement.setString(3, t_brxx.getDLKL());
                statement.setFloat(4, t_brxx.getYCJE());
                statement.setTimestamp(5, t_brxx.getDLRQ());
                statement.execute();
            }
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
        return result ? Optional.of(tBrxxes) : Optional.empty();
    }

    /**
     * 更新信息
     *
     * @param newOne
     * @return
     * @throws IllegalArgumentException
     */
    public static Optional<T_BRXX> updateById(T_BRXX newOne) throws IllegalArgumentException {
        if (newOne == null) throw new IllegalArgumentException("new one can not be null");
        boolean result = true;
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = DatabaseManager.getINSTANCE().getConnection();
            statement = connection.prepareStatement(UPDATE_ONE_BY_ID);
            statement.setString(5, newOne.getBRBH());
            statement.setString(1, newOne.getBRMC());
            statement.setString(2, newOne.getDLKL());
            statement.setFloat(3, newOne.getYCJE());
            statement.setTimestamp(4, newOne.getDLRQ());
            statement.execute();
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
        return result ? Optional.of(newOne) : Optional.empty();

    }
}
