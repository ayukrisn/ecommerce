package com.ayukrisn.ecommerce.httpserver;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;

import java.io.*;
import java.sql.SQLException;

public class ServerHandler implements HttpHandler {
    ResponseHandler responseHandler = new ResponseHandler();
    UserRequestHandler userReqHandler = new UserRequestHandler();
    ProductRequestHandler productReqHandler = new ProductRequestHandler();
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String[] path = exchange.getRequestURI().getPath().split("/");
        String query = exchange.getRequestURI().getQuery();

        if ("GET".equals(exchange.getRequestMethod())) {
            if (path.length <= 1) {
                responseHandler.sendResponse(exchange, 200, "Hello there!");
            }
             if ("users".equals(path[1])) {
                JSONObject jsonUser = null;
                try {
                    jsonUser = userReqHandler.getUser(path);
                    if (jsonUser != null)
                    responseHandler.getResponse(exchange, jsonUser.toString(), path, "users", 200);
                    else {
                        responseHandler.sendResponse(exchange, 404, "ID Not Found");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else if ("products".equals(path[1])) {
                 JSONObject jsonProduct = null;
                 try {
                     jsonProduct = productReqHandler.getProduct(path);
                     if (jsonProduct != null)
                         responseHandler.getResponse(exchange, jsonProduct.toString(), path, "products", 200);
                     else {
                         responseHandler.sendResponse(exchange, 404, "ID Not Found");
                     }
                 } catch (SQLException e) {
                     throw new RuntimeException(e);
                 }
             }

        } else if ("PUT".equals(exchange.getRequestMethod())) {
            // PUT
        } else if ("POST".equals(exchange.getRequestMethod())) {
            // POST
        } else if ("DELETE".equals(exchange.getRequestMethod())) {
            // DELETE
        } else { //untuk request method yang tidak disupport
            handleUnsupportedMethod(exchange);
        }
    }

    private void handleUnsupportedMethod (HttpExchange exchange) throws IOException {
        String response = "RequestHandler method tidak didukung/tidak ada.";
        responseHandler.sendResponse(exchange, 405, response);
    }


//    private String getRequestBody(HttpExchange exchange) throws IOException {
//        InputStream requestBodyStream = exchange.getRequestBody();
//        InputStreamReader requestBodyReader = new InputStreamReader(requestBodyStream);
//        BufferedReader bufferedReader = new BufferedReader(requestBodyReader);
//        StringBuilder requestBody = new StringBuilder();
//        String line;
//
//        while ((line = bufferedReader.readLine()) != null) {
//            requestBody.append(line);
//        }
//
//        bufferedReader.close();
//        requestBodyReader.close();
//        requestBodyStream.close();
//
//        return requestBody.toString();
//    }

}
