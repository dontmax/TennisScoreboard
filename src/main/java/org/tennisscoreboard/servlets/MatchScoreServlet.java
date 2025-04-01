package org.tennisscoreboard.servlets;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.tennisscoreboard.models.CurrentMatch;
import org.tennisscoreboard.repository.HibernateMatchRepository;
import org.tennisscoreboard.service.FinishedMatchesPersistenceService;
import org.tennisscoreboard.service.MatchScoreCalculationService;
import org.tennisscoreboard.service.CurrentMatchesService;

import java.io.IOException;

@WebServlet(name = "MatchScoreServlet", value = "/match-score")
public class MatchScoreServlet extends HttpServlet {
    MatchScoreCalculationService matchScoreCalculationService;
    FinishedMatchesPersistenceService persistenceService;
    CurrentMatchesService currentMatchesService;
    ServletContext servletContext;

    public void init() {
        servletContext = getServletContext();
        currentMatchesService = (CurrentMatchesService) servletContext.getAttribute("currentMatchesService");
        persistenceService = (FinishedMatchesPersistenceService) servletContext.getAttribute("persistenceService");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String match_id = (request.getParameter("uuid"));
        if (currentMatchesService.contains(match_id)) {
            CurrentMatch currentMatch = currentMatchesService.get(match_id);
            request.setAttribute("currentMatch", currentMatch);
            request.setAttribute("uuid", match_id);
            if (currentMatch.getWinner() != null) {
                request.setAttribute("winner", currentMatch.getWinner().getName());
                currentMatchesService.remove(match_id);
            }
            request.getRequestDispatcher("/match-score.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/matches?page=1");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String match_id = (request.getParameter("uuid"));
        CurrentMatch currentMatch = currentMatchesService.get(match_id);
        try {
            if (currentMatch != null && currentMatch.getWinner() == null) {
                matchScoreCalculationService = new MatchScoreCalculationService(currentMatch);
                matchScoreCalculationService.addPoints(Integer.parseInt(request.getParameter("scoreWinnerId")));
                if (currentMatch.getWinner()!=null) {
                    persistenceService.save(currentMatch);
                }
            }
            response.sendRedirect("/match-score?uuid=" + match_id);
        } catch (Exception e) {
            request.setAttribute("message", e.getMessage());
            request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
        }
    }

}