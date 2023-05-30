package com.ayukrisn.ecommerce.httpserver;

import com.ayukrisn.ecommerce.model.Addresses;
import com.ayukrisn.ecommerce.model.OrderDetails;
import com.ayukrisn.ecommerce.model.Orders;
import com.ayukrisn.ecommerce.model.Users;
import com.ayukrisn.ecommerce.persistence.OrderDAO;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderRequestHandler {
    OrderDAO orderDAO = new OrderDAO();
    public JSONObject getOrder(String path[]) throws SQLException {
        JSONObject jsonOrders = new JSONObject();
        int idOrders = Integer.valueOf(path[2]);
        ArrayList<Orders> listOrders = orderDAO.selectOrderById("id", idOrders);
        ArrayList<OrderDetails> listOrderDetails = orderDAO.selectOrderDetailsById(idOrders);
        JSONObject jsonOrderandDetail = new JSONObject();
        JSONArray jsonODetailsArray = new JSONArray();
        JSONArray jsonOrdersArray = new JSONArray();
        for (Orders order : listOrders) {
            JSONObject jsonOrderRecord = new JSONObject();
            jsonOrderRecord.put("id", order.getId());
            jsonOrderRecord.put("buyer", order.getBuyer());
            jsonOrderRecord.put("note", order.getNote());
            jsonOrderRecord.put("total", order.getTotal());
            jsonOrderRecord.put("discount", order.getDiscount());
            jsonOrderRecord.put("is_paid", order.getIs_paid());

            for (OrderDetails details : listOrderDetails) {
                JSONObject jsonDetailRecord = new JSONObject();
                jsonDetailRecord.put("orders", details.getOrder());
                jsonDetailRecord.put("product", details.getProduct());
                jsonDetailRecord.put("quantity", details.getQuantity());
                jsonDetailRecord.put("price", details.getPrice());
                jsonODetailsArray.put(jsonDetailRecord);
            }
            jsonOrderandDetail.put("Orders Record", jsonOrderRecord);
            jsonOrderandDetail.put("Order Details Record", jsonODetailsArray);
            jsonOrdersArray.put(jsonOrderandDetail);
        } jsonOrders.put("Orders based on " + "id", jsonOrdersArray);
        return jsonOrders;
    }

    // POST ORDER
    public String postOrders(JSONObject jsonReqBody) throws SQLException {
        Orders orders = ordersParseJSONData(jsonReqBody);
        return orderDAO.addNewOrder(orders);
    }

    // PUT ORDER
    public String putOrder (JSONObject jsonReqBody, String[] path) throws SQLException {
        Orders orders = ordersParseJSONData(jsonReqBody);
        int idOrder = Integer.valueOf(path[2]);
        return orderDAO.updateOrders(orders, idOrder);
    }

    // DELETE ORDER
    public String deleteOrders(String[] path) throws SQLException, ClassNotFoundException {
        int idOrder = Integer.valueOf(path[2]);
        return orderDAO.deleteOrder(idOrder);
    }

    private Orders ordersParseJSONData(JSONObject jsonReqBody) throws SQLException {
        Orders orders = new Orders();
        System.out.println("Getting data from request");
        orders.setId(jsonReqBody.optInt("id"));
        orders.setBuyer(jsonReqBody.optInt("buyer"));
        orders.setNote(jsonReqBody.optString("note"));
        orders.setTotal(jsonReqBody.optInt("total"));
        orders.setDiscount(jsonReqBody.optInt("discount"));
        orders.setIs_paid(jsonReqBody.optInt("is_paid"));

        return orders;
    }

    // POST ORDER DETAILS
    public String postOrderDetails(JSONObject jsonReqBody) throws SQLException {
        OrderDetails orderDetails = orderDetailsParseJSONData(jsonReqBody);
        return orderDAO.addNewOrderDetail(orderDetails);
    }

    // PUT ORDER DETAIL
    public String putOrderDetails (JSONObject jsonReqBody, String[] path) throws SQLException {
        OrderDetails orderDetails = orderDetailsParseJSONData(jsonReqBody);
        int idOrder = Integer.valueOf(path[2]);
        return orderDAO.updateOrderDetails(orderDetails, idOrder);
    }

    // DELETE ORDER DETAILS
    public String deleteOrderDetails(String[] path) throws SQLException, ClassNotFoundException {
        int idOrder = Integer.valueOf(path[2]);
        return orderDAO.deleteOrderDetails(idOrder);
    }
    private OrderDetails orderDetailsParseJSONData(JSONObject jsonReqBody) throws SQLException {
        OrderDetails orderDetails = new OrderDetails();
        System.out.println("Getting data from request");
        orderDetails.setOrder(jsonReqBody.optInt("orders"));
        orderDetails.setProduct(jsonReqBody.optInt("product"));
        orderDetails.setQuantity(jsonReqBody.optInt("quantity"));
        orderDetails.setPrice(jsonReqBody.optInt("price"));

        return orderDetails;
    }

}
