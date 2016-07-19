package com.joejag.code.orders.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.joejag.code.orders.dto.*;
/**
 * Created by garima05 on 18-07-2016.
 */
public class Access {


    public ArrayList<Order> getOrders(Connection con) throws SQLException
    {
        ArrayList<Order> orderList = new ArrayList<Order>();
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM orderTB");
        ResultSet rs = stmt.executeQuery();
        try
        {
            while(rs.next())
            {
                Order orderObj = new Order();
                orderObj.setOrderId(rs.getInt("orderid"));
                orderObj.setItemId(rs.getInt("itemid"));
                orderObj.setAmount(rs.getInt("amount"));
                orderObj.setStatus(rs.getString("status"));
                orderList.add(orderObj);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return orderList;

    }
}
