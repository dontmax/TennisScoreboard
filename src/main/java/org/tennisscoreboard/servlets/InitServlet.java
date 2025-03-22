package org.tennisscoreboard.servlets;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpServlet;
import org.tennisscoreboard.models.CurrentMatch;

import java.util.HashMap;
import java.util.UUID;

@WebListener
public class InitServlet implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        HashMap <UUID, CurrentMatch> currentMatches = new HashMap<>();
        ServletContext context = sce.getServletContext();
        context.setAttribute("currentMatches", currentMatches);
    }
}
