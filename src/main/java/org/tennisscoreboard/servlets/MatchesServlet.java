package org.tennisscoreboard.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.tennisscoreboard.models.MatchApiDto;
import org.tennisscoreboard.repository.HibernateMatchRepository;
import org.tennisscoreboard.repository.HibernatePlayerRepository;
import org.tennisscoreboard.service.FinishedMatchesPersistenceService;
import org.tennisscoreboard.utils.Pagination;
import org.tennisscoreboard.utils.Validation;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "PlayedMatchesServlet", value = "/matches")
public class MatchesServlet extends HttpServlet {
FinishedMatchesPersistenceService persistenceService;
HibernateMatchRepository matchRepository;
HibernatePlayerRepository playerRepository;
Pagination pagination;

    public void init() {
        matchRepository = new HibernateMatchRepository();
        playerRepository = new HibernatePlayerRepository();
        persistenceService = new FinishedMatchesPersistenceService(matchRepository);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int TABLE_SIZE = 5;
        long matchCount;
        List<MatchApiDto> matches;
        String pathName="";
        String page = request.getParameter("page");
        String playerName = request.getParameter("filter_by_player_name");
        String errorMessage = "No matches found";
        if(playerName!=null&&!Validation.isName(playerName)) {
            request.setAttribute("message", errorMessage);
            playerName = null;
        }
        if(page==null || page.isEmpty() ||Integer.parseInt(page)<1) {
            page="1";
        }
        if(playerName==null || playerName.isEmpty()) {
            matches = persistenceService.getMatches(Integer.parseInt(page)-1, TABLE_SIZE);
            matchCount = persistenceService.getMatchCount();
        } else {
            matches = persistenceService.getMatchesByPlayerName(playerName,Integer.parseInt(page)-1, TABLE_SIZE);
            matchCount = persistenceService.getMatchCountByPlayerName(playerName);
        }
        if(request.getParameter("filter_by_player_name")!=null) {
            pathName="&filter_by_player_name="+request.getParameter("filter_by_player_name");
        }
        pagination = new Pagination(Integer.parseInt(page), matchCount);
        request.setAttribute("pathName", pathName);
        request.setAttribute("pagination", pagination);
        request.setAttribute("matches", matches);
        request.getRequestDispatcher("/matches.jsp").forward(request, response);
    }

}