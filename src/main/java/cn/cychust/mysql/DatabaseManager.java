package cn.cychust.mysql;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @program: hospital-manager-system
 * @description:
 * @author: Yichao Chen
 * @create: 2019-03-21 18:49
 **/
public class DatabaseManager {

    private static DatabaseManager INSTANCE = null;

    private static Connection connection;
    private static ComboPooledDataSource ds;  //数据库连接对象

    private DatabaseManager() throws PropertyVetoException {
        ds = new ComboPooledDataSource();
        ds.setUser("root");
        ds.setPassword("cyc921507");
        ds.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/test1");
        ds.setDriverClass("com.mysql.jdbc.Driver");
        ds.setInitialPoolSize(5);
        ds.setMinPoolSize(1);
        ds.setMaxPoolSize(10);
        ds.setMaxStatements(50);
        ds.setMaxIdleTime(60);
    }

    public static DatabaseManager getINSTANCE() throws PropertyVetoException {
        if (INSTANCE == null) {
            INSTANCE = new DatabaseManager();
        }
        return INSTANCE;
    }

    /**
     * 数据库连接池连接
     *
     * @return
     * @throws SQLException
     */
    public final synchronized Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
