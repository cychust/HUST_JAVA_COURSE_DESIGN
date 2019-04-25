package cn.cychust.data.tghxx.source.dao;

import cn.cychust.comm.Dao;
import cn.cychust.data.tghxx.T_GHXX;
import cn.cychust.mysql.C3p0helper;
import cn.cychust.mysql.DatabaseManager;
import io.reactivex.annotations.NonNull;
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
 * @create: 2019-04-22 18:27
 **/
public class GhxxDao extends Dao {

    private final static Logger LOGGER = Logger.getLogger(GhxxDao.class);
    private final static String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS T_GHXX(" +
                    "GHBH CHAR(6) not NULL, " +
                    "HZBH CHAR(6) not NULL, " +
                    "YSBH CHAR(6) not NULL, " +
                    "BRBH CHAR(6) not NULL, " +
                    "GHRC INT not NULL, " +
                    "THBZ BOOL not NULL, " +
                    "GHFY DECIMAL(8,2) not NULL, " +
                    "RQSJ DATETIME not NULL, " +
                    "PRIMARY KEY ( GHBH )" +
                    ")ENGINE=InnoDB DEFAULT CHARSET=utf8;";
    private final static String FIND_ALL = "";
    private final static String FIND_ONE_BY_GHBH = "SELECT * FROM T_GHXX WHERE GHBH = ?";

    private final static String FIND_ALL_BY_YSBH = "SELECT * FROM T_GHXX WHERE YSBH = ?";
    private final static String ADD_ONE_STATEMENT = "INSERT INTO T_GHXX (GHBH,HZBH,YSBH,BRBH,GHRC,THBZ,GHFY,RQSJ) VALUES(?,?,?,?,?,?,?,?)";


    public static Optional<Boolean> createTable() {
        return createTable(CREATE_TABLE);
    }

    public static synchronized Optional saveOne(@NonNull T_GHXX t_ghxx, @NonNull Timestamp start, float ycje) {
        if (t_ghxx == null) {
            throw new NullPointerException("t_brxx can not be null");
        }
        T_GHXX result = null;
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            int count = 0;
            int bhCount = 0;
            int limitCount = 0;
            connection = DatabaseManager.getINSTANCE().getConnection();
            statement = connection.prepareStatement("SELECT COUNT(*) FROM T_GHXX where RQSJ BETWEEN ? AND ?");
            statement.setTimestamp(1, start);
            statement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            resultSet = statement.executeQuery();
            while (resultSet.next())
                count = resultSet.getInt(1);
            statement = connection.prepareStatement("SELECT GHRS FROM T_HZXX WHERE HZBH = ?");
            statement.setString(1, t_ghxx.getHZBH());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                limitCount = resultSet.getInt(1);
            }
            if (count >= limitCount)
                return Optional.empty();
            statement = connection.prepareStatement("SELECT COUNT(*) FROM T_GHXX");
            resultSet = statement.executeQuery();
            while (resultSet.next())
                bhCount = resultSet.getInt(1);
            statement = connection.prepareStatement(ADD_ONE_STATEMENT);
            t_ghxx.setGHBH(addOne(bhCount));                //
            t_ghxx.setGHRC(count + 1);
            statement.setString(1, t_ghxx.getGHBH());
            statement.setString(2, t_ghxx.getHZBH());
            statement.setString(3, t_ghxx.getYSBH());
            statement.setString(4, t_ghxx.getBRBH());
            statement.setInt(5, t_ghxx.getGHRC());
            statement.setBoolean(6, t_ghxx.isTHBZ());
            statement.setFloat(7, t_ghxx.getGHFY());
            statement.setTimestamp(8, t_ghxx.getRQSJ());
            statement.execute();

            statement = connection.prepareStatement("UPDATE T_BRXX SET YCJE = ? WHERE BRBH = ?");
            statement.setFloat(1, ycje);
            statement.setString(2, t_ghxx.getBRBH());
            statement.executeUpdate();

            result = t_ghxx;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } finally {
            C3p0helper.attemptClose(statement);
            C3p0helper.attemptClose(connection);
            C3p0helper.attemptClose(resultSet);
        }
        return Optional.ofNullable(result);
    }

    public static Optional findAllByYSBH(String ysbh) {

        List<T_GHXX> result = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseManager.getINSTANCE().getConnection();
            statement = connection.prepareStatement(FIND_ALL_BY_YSBH);
            statement.setString(1, ysbh);
            resultSet = statement.executeQuery();
            result = new ArrayList<>();
            while (resultSet.next()) {
                T_GHXX ghxx = new T_GHXX();
                ghxx.setGHBH(resultSet.getString("GHBH"));
                ghxx.setHZBH(resultSet.getString("HZBH"));
                ghxx.setBRBH(resultSet.getString("BRBH"));
                ghxx.setGHFY(resultSet.getFloat("GHFY"));
                ghxx.setGHRC(resultSet.getInt("GHRC"));
                ghxx.setRQSJ(resultSet.getTimestamp("RQSJ"));
                ghxx.setTHBZ(resultSet.getBoolean("THBZ"));
                ghxx.setYSBH(resultSet.getString("YSBH"));
                result.add(ghxx);
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
        return Optional.of(result);
    }

    public static String addOne(Integer count) {
        return String.format("%06d", count + 1);
    }
}
