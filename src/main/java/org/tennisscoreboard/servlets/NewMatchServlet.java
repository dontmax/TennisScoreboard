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
import org.tennisscoreboard.utils.Validation;

@WebServlet(name = "NewMatchServlet", value = "/new-match")
public class NewMatchServlet extends HttpServlet {
    HibernatePlayerRepository playerRepo;

    public void init() {
        playerRepo = new HibernatePlayerRepository();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/new-match.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String firstPlayerName = request.getParameter("firstPlayerName");
        String secondPlayerName = request.getParameter("secondPlayerName");
        String errorMessage = " Player name must begin with uppercase letter, max length: 25 symbols, not \"";
        if(!Validation.isName(firstPlayerName)){
            errorMessage = "First"+errorMessage+firstPlayerName+"\"";
            request.setAttribute("message", errorMessage);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/new-match.jsp");
            try {
                requestDispatcher.forward(request, response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
        }
        if(!Validation.isName(secondPlayerName)){
            errorMessage = "Second"+errorMessage+secondPlayerName+"\"";
            request.setAttribute("message", errorMessage);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/new-match.jsp");
            try {
                requestDispatcher.forward(request, response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
        }
        Player firstPlayer = playerRepo.getByName(firstPlayerName);
        Player secondPlayer = playerRepo.getByName(secondPlayerName);
        if(firstPlayer == null){
            playerRepo.save(new Player(firstPlayerName));
            firstPlayer = playerRepo.getByName(firstPlayerName);
        }
        if(secondPlayer == null){
            playerRepo.save(new Player(secondPlayerName));
            secondPlayer = playerRepo.getByName(secondPlayerName);
        }
        UUID match_id = UUID.randomUUID();
        CurrentMatch currentMatch = new CurrentMatch(firstPlayer, secondPlayer);
        CurrentMatchesService.add(match_id, currentMatch);
        response.sendRedirect("/match-score?uuid="+match_id);
    }

}