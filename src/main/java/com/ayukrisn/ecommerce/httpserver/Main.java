package com.ayukrisn.ecommerce.httpserver;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws IOException{
        try {
            HttpServer httpServer = HttpServer.create(new InetSocketAddress("localhost", 8072), 0);
            // context untuk endpoint "", dgn class untuk handle HTTP RequestHandler
            httpServer.createContext("/",new ServerHandler());
            httpServer.setExecutor(Executors.newSingleThreadExecutor());
            httpServer.start();
            System.out.println("Listening on port 8072");
        } catch (Exception exception){
            exception.printStackTrace();
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
            ServerHandler.processHttpExchange(httpExchange);
        }
    }
 */
