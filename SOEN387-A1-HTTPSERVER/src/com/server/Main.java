/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.server;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;

/**
 *
 * @author Xavier Vani-Charron
 */
public class Main {

    public static void main(String[] args) throws IOException {

        HttpServer server = HttpServer.create(new InetSocketAddress(8500), 0);

        HttpContext root_context = server.createContext("/");
        HttpContext index_context = server.createContext("/index.html");
        HttpContext index2_context = server.createContext("/index2.html");
        HttpContext css_context = server.createContext("/css/css-file1.css");
        HttpContext css2_context = server.createContext("/css/css-file2.css");

        root_context.setHandler(Main::handle);
        index_context.setHandler(Main::handle);
        index2_context.setHandler(Main::handle);
        css_context.setHandler(Main::handle);
        css2_context.setHandler(Main::handle);

        server.start();

    }
    
    private static void handle(HttpExchange exchange) throws IOException {
        URI requestURI = exchange.getRequestURI();
        
        if(requestURI.getPath().equals("/")){
            //root
            handleRootRequest(exchange);
        }else if(requestURI.getPath().equals("/index.html")){
            //index
            handleRootRequest(exchange);
        }else if(requestURI.getPath().equals("/index2.html")){
            //index2
            handleIndexRequest(exchange);
        }else if(requestURI.getPath().equals("/css/css-file1.css")){
            //css1
            handleCssRequestOne(exchange);
        }else if(requestURI.getPath().equals("/css/css-file2.css")){
            //css2
            handleCssRequestTwo(exchange);
        }else{
            //404 blahh
            Headers header = exchange.getResponseHeaders();
            header.set("Content-Type", "text/plain");
            String message = "Requested location not found. 404";
            
            exchange.sendResponseHeaders(404, message.getBytes().length);
            OutputStream out = exchange.getResponseBody();
            out.write(message.getBytes());
            out.close();
        }
    }

    private static void handleRootRequest(HttpExchange exchange) throws IOException {

        Headers header = exchange.getResponseHeaders();
        header.set("Content-Type", "text/html");
        exchange.sendResponseHeaders(200, 0);

        OutputStream out = exchange.getResponseBody();

        byte[] buffer = new byte[4096];
        int count;
        String path = "C:\\Users\\Xavier Vani-Charron\\Documents\\NetBeansProjects\\SOEN387-A1-HTTPSERVER\\src\\static-files\\index.html";
//      String path = "/static-files/index.html";

        try (FileInputStream index = new FileInputStream(path)) {
            while ((count = index.read(buffer)) >= 0) {
                out.write(buffer, 0, count);
            }
        } catch (FileNotFoundException fnfe) {
            //needs to be 404 not found
            fnfe.printStackTrace();
        }

        out.close();
    }

    private static void handleIndexRequest(HttpExchange exchange) throws IOException {

        URI requestURI = exchange.getRequestURI();
        Headers header = exchange.getResponseHeaders();
        
        if (requestURI.getPath().equals("/index2.html")) {
            //process the root request
            header.set("Content-Type", "text/html");
            exchange.sendResponseHeaders(200, 0);
            OutputStream out = exchange.getResponseBody();

            byte[] buffer = new byte[4096];
            int count;
            String path = "C:\\Users\\Xavier Vani-Charron\\Documents\\NetBeansProjects\\SOEN387-A1-HTTPSERVER\\src\\static-files\\index2.html";
//          String path = "/static-files/index.html";

            try (FileInputStream index = new FileInputStream(path)) {
                while ((count = index.read(buffer)) >= 0) {
                    out.write(buffer, 0, count);
                }
            } catch (FileNotFoundException fnfe) {
                //needs to be 404 not found
                fnfe.printStackTrace();
            }

            out.close();
        }
        
    }

    private static void handleCssRequestOne(HttpExchange exchange) throws IOException {

        Headers header = exchange.getResponseHeaders();
        header.set("Content-Type", "text/css");
        exchange.sendResponseHeaders(200, 0);

        OutputStream out = exchange.getResponseBody();

        byte[] buffer = new byte[4096];
        int count;
        String path = "C:\\Users\\Xavier Vani-Charron\\Documents\\NetBeansProjects\\SOEN387-A1-HTTPSERVER\\src\\static-files\\css\\css-file1.css";
//      String path = "/static-files/index.html";

        try (FileInputStream index = new FileInputStream(path)) {
            while ((count = index.read(buffer)) >= 0) {
                out.write(buffer, 0, count);
            }
        } catch (FileNotFoundException fnfe) {
            //needs to be 404 not found
            fnfe.printStackTrace();
        }

        out.close();

    }

    private static void handleCssRequestTwo(HttpExchange exchange) throws IOException {

        Headers header = exchange.getResponseHeaders();
        header.set("Content-Type", "text/css");
        exchange.sendResponseHeaders(200, 0);

        OutputStream out = exchange.getResponseBody();

        byte[] buffer = new byte[4096];
        int count;
        String path = "C:\\Users\\Xavier Vani-Charron\\Documents\\NetBeansProjects\\SOEN387-A1-HTTPSERVER\\src\\static-files\\css\\css-file2.css";
//      String path = "/static-files/index.html";

        try (FileInputStream index = new FileInputStream(path)) {
            while ((count = index.read(buffer)) >= 0) {
                out.write(buffer, 0, count);
            }
        } catch (FileNotFoundException fnfe) {
            //needs to be 404 not found
            fnfe.printStackTrace();
        }

        out.close();

    }
}
