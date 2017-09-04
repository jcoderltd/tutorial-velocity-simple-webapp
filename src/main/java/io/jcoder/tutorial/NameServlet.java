/*
 * Copyright 2017 - JCoder Ltd
 */
package io.jcoder.tutorial;

import java.io.IOException;

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

        resp.getWriter().println(processedName);
    }
}
