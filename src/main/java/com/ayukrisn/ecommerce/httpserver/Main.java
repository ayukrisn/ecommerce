package com.ayukrisn.ecommerce.httpserver;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws IOException{
        HttpServer httpServer = HttpServer.create(new InetSocketAddress("localhost", 8072), 0);
        // context untuk endpoint "shopvista", dgn class untuk handle HTTP Request
        httpServer.createContext("/shopvista",new HttpResponseHandler());
        httpServer.setExecutor(Executors.newSingleThreadExecutor());
        httpServer.start();
    }

    public static class HttpResponseHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("GET".equals(exchange.getRequestMethod())) {
                String response = "Hello!";
                sendResponse(exchange, 200, response);
            } else if ("PUT".equals(exchange.getRequestMethod())) {
                //
            } else if ("POST".equals(exchange.getRequestMethod())) {
                //
            } else if ("DELETE".equals(exchange.getRequestMethod())) {
                //
            } else { //untuk request method yang tidak disupport
                handleUnsupportedMethod(exchange);
            }
        }

        private void handleUnsupportedMethod(HttpExchange exchange) throws IOException {
            String response = "Request method tidak didukung";
            sendResponse(exchange, 405, response);
        }

        // Method untuk mengirim respon ke client dan mengatur
        //status code, response length, dan menulis respon ke output stream
        private void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
            OutputStream outputStream = exchange.getResponseBody();
            exchange.sendResponseHeaders(200, response.length());
            outputStream.write(response.getBytes());
            outputStream.flush();
            outputStream.close();
        }
    }

}
