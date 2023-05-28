package com.ayukrisn.ecommerce.httpserver;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
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
                //Example, masih gatau ini harusnya isi apa
                //Isi: Bisa memberikan resource yang diminta

                String response = "Hello! You've sent a GET Request";
                sendResponse(exchange, 200, response);
            } else if ("PUT".equals(exchange.getRequestMethod())) {
                //Example, masih gatau ini harusnya isi apa
                //Isi: update resource atau operasi lainnya

                String requestBody = getRequestBody(exchange);
                String response = "Hello! You've sent a PUT Request: " + requestBody;
                sendResponse(exchange, 200, response);
            } else if ("POST".equals(exchange.getRequestMethod())) {
                //Example, masih gatau ini harusnya isi apa
                //Isi: memproses requestBody dan save data yg diberikan

                String requestBody = getRequestBody(exchange); //Mendapatkan data yang diberikan
                String response = "Hello! You've sent a POST Request: " + requestBody;
                sendResponse(exchange, 200, response);
            } else if ("DELETE".equals(exchange.getRequestMethod())) {
                //Example, masih gatau ini harusnya isi apa
                //Isi: menghapus sesuai request

                String response = "Hello! You've sent a DELETE Request";
                sendResponse(exchange, 200, response);
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

        private String getRequestBody(HttpExchange exchange) throws IOException {
            InputStream requestBodyStream = exchange.getRequestBody();
            InputStreamReader requestBodyReader = new InputStreamReader(requestBodyStream);
            BufferedReader bufferedReader = new BufferedReader(requestBodyReader);
            StringBuilder requestBody = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                requestBody.append(line);
            }

            bufferedReader.close();
            requestBodyReader.close();
            requestBodyStream.close();

            return requestBody.toString();
        }

    }
}

/*
    private class RequestHandler implements HttpHandler {
        public void handle (HttpExchange httpExchange) throws IOException {
            PrintStream out = new PrintStream(httpExchange.getResponseBody());
            handle(httpExchange, out);
        }

        private void handle(HttpExchange httpExchange, PrintStream out) {
            URI uri = httpExchange.getRequestURI();
            String path = uri.getPath();
            System.out.printf("path: %s\n", path);
            Server.processHttpExchange(httpExchange);
        }
    }
 */
