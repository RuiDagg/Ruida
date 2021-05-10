package com.pangsir.bookstore.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 获取和关闭数据库连接的工具类
 */
public class JdbcUtils {


    private static DataSource dataSource = null;

    static {
        dataSource = new ComboPooledDataSource("helloc3p0");
    }

    //获取数据库连接的方法
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    //关闭数据库连接的方法
    public static void release(Connection connection){

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //关闭结果集，PreparedStatement对象，及数据库连接的方法
    public static void release(ResultSet resultSet, PreparedStatement ps, Connection connection){

        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
