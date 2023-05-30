package com.ayukrisn.ecommerce.httpserver;

import com.ayukrisn.ecommerce.model.OrderDetails;
import com.ayukrisn.ecommerce.model.Reviews;
import com.ayukrisn.ecommerce.persistence.ReviewDAO;
import org.json.JSONObject;

import java.sql.SQLException;

public class ReviewRequestHandler {
    ReviewDAO reviewDAO = new ReviewDAO();
    // POST ORDER DETAILS
    public String postReview(JSONObject jsonReqBody) throws SQLException {
        Reviews review = new Reviews();
        System.out.println("Getting data from request");
        review.setOrder(jsonReqBody.optInt("orders"));
        review.setStar(jsonReqBody.optInt("star"));
        review.setDescription(jsonReqBody.optString("description"));

        return reviewDAO.addNewReview(review);
    }
}
