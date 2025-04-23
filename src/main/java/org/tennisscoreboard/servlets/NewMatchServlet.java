package org.tennisscoreboard.servlets;

import java.io.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.tennisscoreboard.service.CurrentMatchesService;
import org.tennisscoreboard.service.ValidationService;

@WebServlet(name = "NewMatchServlet", value = "/new-match")
public class NewMatchServlet extends HttpServlet {
    CurrentMatchesService currentMatchesService;
    ServletContext servletContext;

    public void init() {
        servletContext = getServletContext();
        currentMatchesService = (CurrentMatchesService) servletContext.getAttribute("currentMatchesService");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/new-match.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String firstPlayerName = request.getParameter("firstPlayerName");
        String secondPlayerName = request.getParameter("secondPlayerName");
        String validationResult = ValidationService.validateNames(firstPlayerName, secondPlayerName);
        if (!validationResult.isBlank()) {
            request.setAttribute("message", validationResult);
            request.getRequestDispatcher("/new-match.jsp").forward(request, response);
            return;
        }
        try {
            String match_id = currentMatchesService.add(firstPlayerName, secondPlayerName);
            response.sendRedirect("/match-score?uuid=" + match_id);
        } catch (Exception e) {
            request.setAttribute("message", e.getMessage());
            request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
        }
    }

}