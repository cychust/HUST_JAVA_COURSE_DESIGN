package cn.cychust.data.source.local;

import cn.cychust.bean.T_BRXX;
import cn.cychust.mysql.DatabaseManager;
import org.apache.log4j.Logger;

import java.beans.PropertyVetoException;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @program: hospital-manager-system
 * @description:
 * @author: Yichao Chen
 * @create: 2019-03-22 16:26
 **/
public class userDao {

    private static final Logger LOGGER = Logger.getLogger(userDao.class);

    public static ResultSet findAllUser() {
        ResultSet resultSet = null;
        try {
            Connection connection = DatabaseManager.getINSTANCE().getConnection();
            resultSet = connection.createStatement().executeQuery("select  * from  emp");
            while (resultSet.next()) {
                LOGGER.info(resultSet.getObject(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public static Optional<T_BRXX> findOne(String user, String pass) {
        try {
            ResultSet resultSet = null;
            Connection connection = DatabaseManager.getINSTANCE().getConnection();
            resultSet = connection.createStatement().executeQuery("select  * from  user where username=user and password=pass");
            if (!resultSet.wasNull()) {
                T_BRXX tBrxx = new T_BRXX();
                tBrxx.setBRBH(resultSet.getString(0));
                tBrxx.setBRMC(resultSet.getString(1));
                tBrxx.setDLKL(resultSet.getString(2));
                tBrxx.setDLRQ(resultSet.getDate(3));
                tBrxx.setYCJE(resultSet.getString(4));
                return Optional.of(tBrxx);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(null);
    }

    public static Optional<List<T_BRXX>> findAll() {
        List<T_BRXX> list = new ArrayList<>();
        try {
            ResultSet resultSet = null;
            Connection connection = DatabaseManager.getINSTANCE().getConnection();
            resultSet = connection.createStatement().executeQuery("select  * from  t_brxx");
            while (resultSet.next()) {
                T_BRXX tBrxx = new T_BRXX();
                tBrxx.setBRBH(resultSet.getString(0));
                tBrxx.setBRMC(resultSet.getString(1));
                tBrxx.setDLKL(resultSet.getString(2));
                tBrxx.setDLRQ(resultSet.getDate(3));
                tBrxx.setYCJE(resultSet.getString(4));
                list.add(tBrxx);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(list);
    }

    public static Optional<T_BRXX> addOne(T_BRXX t_brxx) {

    }
}
