package com.ayukrisn.ecommerce.httpserver;

import com.ayukrisn.ecommerce.model.Addresses;
import com.ayukrisn.ecommerce.model.Users;
import com.ayukrisn.ecommerce.persistence.AddressDAO;
import com.ayukrisn.ecommerce.persistence.UserDAO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserRequestHandler{
    UserDAO userDAO = new UserDAO();
    AddressDAO addressDAO = new AddressDAO();
    public JSONObject getUser(String[] path) throws SQLException {
        JSONObject jsonUser = null;
        if (path.length == 2) {
            jsonUser = new JSONObject();
            JSONArray jsonUserArray = new JSONArray();
            ArrayList<Users> listUsers = userDAO.selectAll();
            for (Users user : listUsers) {
                JSONObject jsonUserRecord = new JSONObject();
                jsonUserRecord.put("id", user.getId());
                jsonUserRecord.put("first_name", user.getFirst_name());
                jsonUserRecord.put("last_name", user.getLast_name());
                jsonUserRecord.put("email", user.getEmail());
                jsonUserRecord.put("phone_number", user.getPhone_number());
                jsonUserRecord.put("type", user.getType());
                jsonUserArray.put(jsonUserRecord);
            }
            jsonUser.put("User Record", jsonUserArray);
        }
        else if (path.length == 3){
            jsonUser = new JSONObject();
            int idUser = Integer.valueOf(path[2]);
            Users user = userDAO.selectUserById(idUser);
            JSONArray jsonAddressesArray = new JSONArray();
            ArrayList<Addresses> listAddresses = addressDAO.selectAddressesByUser(idUser);
            if (user.getId() != 0) {
                JSONObject jsonUserRecord = new JSONObject();
                jsonUserRecord.put("id", user.getId());
                jsonUserRecord.put("first_name", user.getFirst_name());
                jsonUserRecord.put("last_name", user.getLast_name());
                jsonUserRecord.put("email", user.getEmail());
                jsonUserRecord.put("phone_number", user.getPhone_number());
                for (Addresses address : listAddresses) {
                    JSONObject jsonAddressRecord = new JSONObject();
                    jsonAddressRecord.put("user", address.getUser());
                    jsonAddressRecord.put("type", address.getType());
                    jsonAddressRecord.put("line1", address.getLine1());
                    jsonAddressRecord.put("line2", address.getLine2());
                    jsonAddressRecord.put("city", address.getCity());
                    jsonAddressRecord.put("province", address.getProvince());
                    jsonAddressRecord.put("postcode", address.getPostcode());
                    jsonAddressesArray.put(jsonAddressRecord);
                }
                jsonUser.put("User Record", jsonUserRecord);
                jsonUser.put("Addresses Record", jsonAddressesArray);
            } else {
                jsonUser = null;
            }

        }
        return jsonUser;
    }
}