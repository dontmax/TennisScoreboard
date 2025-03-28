package org.tennisscoreboard.servlets;

import java.io.*;
import java.util.UUID;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.tennisscoreboard.models.CurrentMatch;
import org.tennisscoreboard.models.Player;
import org.tennisscoreboard.repository.HibernatePlayerRepository;
import org.tennisscoreboard.service.CurrentMatchesService;
import org.tennisscoreboard.service.ValidationService;

@WebServlet(name = "NewMatchServlet", value = "/new-match")
public class NewMatchServlet extends HttpServlet {
    HibernatePlayerRepository playerRepo;
    ValidationService validationService;

    public void init() {
        playerRepo = new HibernatePlayerRepository();
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
            playerRepo.save(new Player(firstPlayerName));
            playerRepo.save(new Player(secondPlayerName));
            Player firstPlayer = playerRepo.getByName(firstPlayerName);
            Player secondPlayer = playerRepo.getByName(secondPlayerName);
            String match_id = UUID.randomUUID().toString();
            CurrentMatch currentMatch = new CurrentMatch(firstPlayer, secondPlayer);
            CurrentMatchesService.add(match_id, currentMatch);
            response.sendRedirect("/match-score?uuid=" + match_id);
        } catch (Exception e) {
            request.setAttribute("message", e.getMessage());
            request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
        }
    }

}