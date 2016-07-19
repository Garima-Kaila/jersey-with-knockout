package com.joejag.code.orders.dao;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 * Created by garima05 on 18-07-2016.
 */
public class Database {
    public Connection getConnection() throws Exception
    {
        try
        {
            String connectionURL = "jdbc:mysql://localhost:3306/mysql";
            Connection connection = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(connectionURL, "root", "");
            return connection;
        } catch (Exception e)
        {
            throw e;
        }

    }

}