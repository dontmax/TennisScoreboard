package org.tennisscoreboard.servlets;

import java.io.*;
import java.util.UUID;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.tennisscoreboard.dto.PlayerDTO;
import org.tennisscoreboard.models.CurrentMatch;
import org.tennisscoreboard.service.CurrentMatchesService;
import org.tennisscoreboard.service.ValidationService;

@WebServlet(name = "NewMatchServlet", value = "/new-match")
public class NewMatchServlet extends HttpServlet {
    ValidationService validationService;
    CurrentMatchesService currentMatchesService;
    ServletContext servletContext;
    public void init() {
        servletContext = getServletContext();
        currentMatchesService = (CurrentMatchesService) servletContext.getAttribute("currentMatchesService");
        validationService = new ValidationService();
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
        if (!validationService.validateNames(firstPlayerName, secondPlayerName)) {
            request.setAttribute("message", validationService.getErrorMessage());
            request.getRequestDispatcher("/new-match.jsp").forward(request, response);
            return;
        }
        try {
            PlayerDTO player1 = new PlayerDTO(1, firstPlayerName);
            PlayerDTO player2 = new PlayerDTO(2, secondPlayerName);
            String match_id = UUID.randomUUID().toString();
            CurrentMatch currentMatch = new CurrentMatch(player1, player2);
            currentMatchesService.add(match_id, currentMatch);
            response.sendRedirect("/match-score?uuid=" + match_id);
        } catch (Exception e) {
            request.setAttribute("message", e.getMessage());
            request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
        }
    }

}