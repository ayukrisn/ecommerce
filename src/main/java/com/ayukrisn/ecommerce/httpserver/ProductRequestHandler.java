package com.ayukrisn.ecommerce.httpserver;

import com.ayukrisn.ecommerce.model.Products;
import com.ayukrisn.ecommerce.model.Users;
import com.ayukrisn.ecommerce.persistence.ProductDAO;

import com.ayukrisn.ecommerce.persistence.UserDAO;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductRequestHandler {

    ProductDAO productDAO = new ProductDAO();
    UserDAO userDAO = new UserDAO();
    public JSONObject getProduct(String[] path) throws SQLException {
        JSONObject jsonProduct = null;
        if (path.length == 2) {
            jsonProduct = new JSONObject();
            JSONArray jsonUserArray = new JSONArray();
            ArrayList<Products> listProducts = productDAO.selectAll();
            for (Products product : listProducts) {
                JSONObject jsonUserRecord = new JSONObject();
                jsonUserRecord.put("id", product.getId());
                jsonUserRecord.put("seller", product.getSeller());
                jsonUserRecord.put("title", product.getTitle());
                jsonUserRecord.put("description", product.getDescription());
                jsonUserRecord.put("price", product.getPrice());
                jsonUserRecord.put("stock", product.getStock());
                jsonUserArray.put(jsonUserRecord);
            }
            jsonProduct.put("Product Record", jsonUserArray);
        }
        else if (path.length == 3){
            jsonProduct = new JSONObject();
            int idProduct = Integer.valueOf(path[2]);
            Products product = productDAO.selectProductById(idProduct);
            Users user = userDAO.selectUserById(product.getId());
            if (product.getId() != 0) {
                JSONObject jsonProductRecord = new JSONObject();
                jsonProductRecord.put("id", product.getId());
                jsonProductRecord.put("seller", product.getSeller());
                jsonProductRecord.put("title", product.getTitle());
                jsonProductRecord.put("description", product.getDescription());
                jsonProductRecord.put("price", product.getPrice());
                jsonProductRecord.put("stock", product.getStock());

                JSONObject jsonSellerRecord = new JSONObject();
                jsonSellerRecord.put("id", user.getId());
                jsonSellerRecord.put("first_name", user.getFirst_name());
                jsonSellerRecord.put("last_name", user.getLast_name());
                jsonSellerRecord.put("email", user.getEmail());
                jsonSellerRecord.put("phone_number", user.getPhone_number());

                jsonProduct.put("Product Record", jsonProductRecord);
                jsonProduct.put("Seller Record", jsonSellerRecord);
            } else {
                jsonProduct = null;
            }

        }
        return jsonProduct;
    }
}
