package com.joejag.code.orders.model;
import com.joejag.code.orders.dao.Database;
import com.joejag.code.orders.dto.*;
import com.joejag.code.orders.dao.*;

import java.sql.Connection;
import java.util.ArrayList;


/**
 * Created by garima05 on 18-07-2016.
 */
public class AccessManager {
    public ArrayList<Order> getOrders() throws Exception
    {
        ArrayList<Order> orderList = new ArrayList<Order>();
        Database db = new Database();
        Connection con = db.getConnection();
        Access access = new Access();
        orderList = access.getOrders(con);
        return orderList;
    }
}


