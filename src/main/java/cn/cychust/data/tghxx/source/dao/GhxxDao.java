package cn.cychust.data.tghxx.source.dao;

import cn.cychust.comm.Dao;
import cn.cychust.data.tghxx.T_GHXX;
import cn.cychust.data.tksys.T_KSYS;
import cn.cychust.mysql.C3p0helper;
import cn.cychust.mysql.DatabaseManager;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

/**
 * @program: HUST_JAVA_COURSE_DESIGN
 * @description:
 * @author: Yichao Chen
 * @create: 2019-04-22 18:27
 **/
public class GhxxDao extends Dao {

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

    private final static String ADD_ONE_STATEMENT = "INSERT INTO T_GHXX (GHBH,HZBH,YSBH,BRBH,GHRC,THBZ,GHFY,RQSJ) VALUES(?,?,?,?,?,?,?,?)";


    public static Optional<Boolean> createTable() {
        return createTable(CREATE_TABLE);
    }

    public static Optional saveOne(T_GHXX t_ghxx) {
        if (t_ghxx == null) {
            throw new NullPointerException("t_brxx can not be null");
        }
        T_GHXX result = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseManager.getINSTANCE().getConnection();
            statement = connection.prepareStatement(ADD_ONE_STATEMENT);
            statement.setString(1, t_ghxx.getGHBH());
            statement.setString(2, t_ghxx.getHZBH());
            statement.setString(3, t_ghxx.getYSBH());
            statement.setString(4, t_ghxx.getBRBH());
            statement.setInt(5, t_ghxx.getGHRC());
            statement.setBoolean(6, t_ghxx.isTHBZ());
            statement.setFloat(7, t_ghxx.getGHFY());
            statement.setTimestamp(8, t_ghxx.getRQSJ());
            statement.execute();
            result = t_ghxx;
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
