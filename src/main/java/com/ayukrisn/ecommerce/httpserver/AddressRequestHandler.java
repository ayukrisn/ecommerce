package com.ayukrisn.ecommerce.httpserver;

import com.ayukrisn.ecommerce.model.Addresses;
import com.ayukrisn.ecommerce.model.Users;
import com.ayukrisn.ecommerce.persistence.AddressDAO;
import org.json.JSONObject;

import java.sql.SQLException;

public class AddressRequestHandler {

    AddressDAO addressDAO = new AddressDAO();
    public String postAddresses(JSONObject jsonReqBody) throws SQLException {
        Addresses address = addressParseJSONData(jsonReqBody);
        return addressDAO.addNewAddress(address);
    }

    // PUT ADDRESS (UPDATE in database)
    public String putAddress (JSONObject jsonReqBody, String[] path) throws SQLException {
        Addresses address = addressParseJSONData(jsonReqBody);
        int idUser = Integer.valueOf(path[2]);

        return addressDAO.updateAddress(address, idUser);
    }

    private Addresses addressParseJSONData(JSONObject jsonReqBody) throws SQLException {
        Addresses address = new Addresses();
        address.setUser(jsonReqBody.optInt("user"));
        address.setType(jsonReqBody.optString("type"));
        address.setLine1(jsonReqBody.optString("line1"));
        address.setLine2(jsonReqBody.optString("line2"));
        address.setCity(jsonReqBody.optString("city"));
        address.setProvince(jsonReqBody.optString("province"));
        address.setPostcode(jsonReqBody.optString("postcode"));

        return address;
    }
}
