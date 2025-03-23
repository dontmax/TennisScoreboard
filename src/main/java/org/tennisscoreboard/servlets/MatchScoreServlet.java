package org.tennisscoreboard.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.tennisscoreboard.models.CurrentMatch;
import org.tennisscoreboard.models.Match;
import org.tennisscoreboard.repository.HibernateMatchRepository;
import org.tennisscoreboard.service.FinishedMatchesPersistenceService;
import org.tennisscoreboard.service.MatchScoreCalculationService;
import org.tennisscoreboard.service.CurrentMatchesService;

import java.io.IOException;

@WebServlet(name = "MatchScoreServlet", value = "/match-score")
public class MatchScoreServlet extends HttpServlet {
    HibernateMatchRepository matchRepository;
    MatchScoreCalculationService matchScoreCalculationService;
    FinishedMatchesPersistenceService finishedMatchesPersistenceService;

    public void init(){
        matchRepository = new HibernateMatchRepository();
        finishedMatchesPersistenceService = new FinishedMatchesPersistenceService(matchRepository);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String match_id = (request.getParameter("uuid"));
        CurrentMatch currentMatch = CurrentMatchesService.get(match_id);
        redirect(request, response, match_id, currentMatch);
    }

    private void redirect(HttpServletRequest request, HttpServletResponse response, String match_id, CurrentMatch currentMatch) throws IOException {
        request.setAttribute("currentMatch", currentMatch);
        request.setAttribute("uuid", match_id);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/match-score.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String match_id = (request.getParameter("uuid"));
        CurrentMatch currentMatch = CurrentMatchesService.get(match_id);
        if(currentMatch!= null) {
            matchScoreCalculationService = new MatchScoreCalculationService(currentMatch);
            matchScoreCalculationService.addPointsTo(Integer.parseInt(request.getParameter("scoreWinnerId")));
            if (matchScoreCalculationService.isMatchOver()) {
                finishedMatchesPersistenceService.save(new Match(
                        currentMatch.getFirstPlayer(),
                        currentMatch.getSecondPlayer(),
                        currentMatch.getWinnerPlayer()));
                request.setAttribute("winner", currentMatch.getWinnerPlayer().getName());
                CurrentMatchesService.remove(match_id);
                redirect(request, response, match_id, currentMatch);
            }
        }
        response.sendRedirect("/match-score?uuid=" + match_id);
    }

}