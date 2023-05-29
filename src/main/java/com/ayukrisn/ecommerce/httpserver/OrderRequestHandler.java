package com.ayukrisn.ecommerce.httpserver;

import com.ayukrisn.ecommerce.model.OrderDetails;
import com.ayukrisn.ecommerce.model.Orders;
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
        Orders order = orderDAO.selectOrderById("id", idOrders);
        ArrayList<OrderDetails> listOrderDetails = orderDAO.selectOrderDetailsById(idOrders);
        JSONArray jsonODetailsArray = new JSONArray();
        if (order.getId() != 0) {
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
            jsonOrders.put("Order Record", jsonOrderRecord);
            jsonOrders.put("Order Details Record", jsonODetailsArray);

        } else {
            jsonOrders = null;
        }
        return jsonOrders;
    }

}
