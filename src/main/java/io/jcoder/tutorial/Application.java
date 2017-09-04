/*
 * Copyright 2017 - JCoder Ltd
 */
package io.jcoder.tutorial;

import org.apache.velocity.tools.view.VelocityViewServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class Application {

    public static void main(String[] args) throws Exception {
        // create a new HTTP server that listens on port 8080
        Server httpServer = new Server(8080);

        // create a new servlet context where we'll add the servlets
        ServletContextHandler servletContext = new ServletContextHandler(null, "/", true, false);

        // configure the VelocityViewServlet (from the velocity-tools library)
        servletContext.addServlet(VelocityViewServlet.class, "*.vm");

        // add and configure the DefaultServlet from Jetty to handle correctly the welcome file redirection
        servletContext.setInitParameter("org.eclipse.jetty.servlet.Default.welcomeServlets", "true");
        servletContext.setInitParameter("org.eclipse.jetty.servlet.Default.dirAllowed", "false");
        servletContext.addServlet(DefaultServlet.class, "/");

        // add a resource base to tell Jetty where we'll be serving static files from (in case you want
        // to load e.g. images/css/other static files)
        // Note: We won't use this, but need to configure a resource base for the DefaultServlet to work
        // correctly
        servletContext.setResourceBase("./");

        servletContext.setWelcomeFiles(new String[] { "index.vm" });

        // tell the httpServer to use the servletContext to handle incoming HTTP
        // requests
        httpServer.setHandler(servletContext);

        httpServer.start();

        // wait for the server to stop before we stop the application
        httpServer.join();
    }

}
