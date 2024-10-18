package com.webprog.demo1.webservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "HelloWorldServlet", urlPatterns = {"", "/hello"})
public class HelloWorldServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //PrintWriter
        PrintWriter pw = resp.getWriter();

        //Get Properties
        String name = req.getParameter("name");

        if(name == null){
            name = "Johnny";
        }

        //Write response
        pw.format("<html><head></head><body><h1>Hewwo %s</h1></body></html>",name);
        pw.flush();
    }
}
