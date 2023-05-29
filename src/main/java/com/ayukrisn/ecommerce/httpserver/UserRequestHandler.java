package com.ayukrisn.ecommerce.httpserver;

import com.ayukrisn.ecommerce.model.Users;
import com.ayukrisn.ecommerce.persistence.UserDAO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserRequestHandler{
    UserDAO userDAO = new UserDAO();
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
            jsonUser.put("User Data", jsonUserArray);
        }
        else if (path.length == 3){
            jsonUser = new JSONObject();
            int idUser = Integer.valueOf(path[2]);
            Users user = userDAO.selectUserById(idUser);
            jsonUser.put("id", user.getId());
            jsonUser.put("first_name", user.getFirst_name());
            jsonUser.put("last_name", user.getLast_name());
            jsonUser.put("email", user.getEmail());
            jsonUser.put("phone_number", user.getPhone_number());
            jsonUser.put("type", user.getType());
        }
        return jsonUser;
    }
}