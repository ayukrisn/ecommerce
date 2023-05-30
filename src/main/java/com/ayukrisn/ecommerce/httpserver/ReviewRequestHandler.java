package com.ayukrisn.ecommerce.httpserver;

import com.ayukrisn.ecommerce.model.Addresses;
import com.ayukrisn.ecommerce.model.OrderDetails;
import com.ayukrisn.ecommerce.model.Reviews;
import com.ayukrisn.ecommerce.persistence.ReviewDAO;
import org.json.JSONObject;

import java.sql.SQLException;

public class ReviewRequestHandler {
    ReviewDAO reviewDAO = new ReviewDAO();
    // POST REVIEWS
    public String postReview(JSONObject jsonReqBody) throws SQLException {
        Reviews review = reviewParseJSONData(jsonReqBody);
        return reviewDAO.addNewReview(review);
    }

    // PUT REVIEWS (UPDATE in database)
    public String putReview (JSONObject jsonReqBody, String[] path) throws SQLException {
        Reviews review = reviewParseJSONData(jsonReqBody);
        int idOrder = Integer.valueOf(path[2]);

        return reviewDAO.updateReview(review, idOrder);
    }

    // DELETE REVIEW
    public String deleteReview(String[] path) throws SQLException, ClassNotFoundException {
        int idOrder = Integer.valueOf(path[2]);
        return reviewDAO.deleteReview(idOrder);
    }

    private Reviews reviewParseJSONData(JSONObject jsonReqBody) throws SQLException {
        Reviews review = new Reviews();
        System.out.println("Getting data from request");
        review.setOrder(jsonReqBody.optInt("orders"));
        review.setStar(jsonReqBody.optInt("star"));
        review.setDescription(jsonReqBody.optString("description"));

        return review;
    }
}
