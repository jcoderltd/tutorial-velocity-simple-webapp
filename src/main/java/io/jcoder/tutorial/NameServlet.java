/*
 * Copyright 2017 - JCoder Ltd
 */
package io.jcoder.tutorial;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");

        String processedName = String.format("%s %s %s", title, firstName, lastName).toUpperCase();

        // add attributes that can be used by the Velocity template (e.g. using $processedName)
        req.setAttribute("processedName", processedName);
        req.setAttribute("numberOfCharacters", processedName.length());

        // forward the request to the Velocity template, using a dispatcher
        // (this is really the main part that connects our View with the VelocityViewServlet)
        RequestDispatcher dispatcher = req.getRequestDispatcher("/name.vm");
        dispatcher.forward(req, resp);
    }
}
