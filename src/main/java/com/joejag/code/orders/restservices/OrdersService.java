package com.joejag.code.orders.restservices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Map;
import java.util.TreeMap;

import com.google.gson.Gson;
import com.joejag.code.orders.dto.*;
import com.joejag.code.orders.model.*;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
/**
 * curl -X PUT http://127.0.0.1:9090/orders-server/orders/1?customer_name=bob 
 * curl -X GET http://127.0.0.1:9090/orders-server/orders/1 
 * curl -X GET http://127.0.0.1:9090/orders-server/orders/list
 */

@Path("orders")
public class OrdersService
{
   @GET
     @Produces("application/json")
   public Response courses() {
      String orders = null;



      ArrayList<Order> orderList = new ArrayList<Order>();
      try {
         orderList = new AccessManager().getOrders();

         Gson gson = new Gson();
         orders = gson.toJson(orderList);
      } catch (Exception e) {
         e.printStackTrace();
      }
      return  Response.ok(orders, MediaType.APPLICATION_JSON).build();
   }



   @POST
   @Path("/orderList")

   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   @Produces(MediaType.APPLICATION_FORM_URLENCODED)
   // @WebMethod(operationName = "insert")
   public void insertOrderData(@FormParam("orderid") int orderid,
                      @FormParam("itemid") int itemid, @FormParam("amount") int amount, @FormParam("status") String status) {
      try {
         Class.forName("com.mysql.jdbc.Driver");
         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "");

         String query = "Insert into orderTB (orderid,itemid,amount,status) values(?,?,?,?)";
         PreparedStatement st = con.prepareStatement(query);
         st.setInt(1, orderid);
         st.setInt(2, itemid);
         st.setInt(3, amount);
         st.setString(4, status);

         st.executeUpdate();
      }
      catch (Exception e) {
         System.out.println(e.getMessage());
      }

   }
   @POST
   @Path("/orderdata")
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   // @WebMethod(operationName = "insert")
   public Order addOrderRecord(Order data) {
      try {
         Class.forName("com.mysql.jdbc.Driver");
         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "");

         String query = "Insert into orderTB (orderid,itemid,amount,status) values(?,?,?,?)";
         PreparedStatement st = con.prepareStatement(query);
         st.setInt(1, data.orderId);
         st.setInt(2, data.itemId);
         st.setInt(3, data.amount);
         st.setString(4, data.status);

         st.executeUpdate();
      }
      catch (Exception e) {
         System.out.println(e.getMessage());
      }
      return data;
   }
}