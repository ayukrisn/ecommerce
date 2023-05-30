package com.ayukrisn.ecommerce.httpserver;

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