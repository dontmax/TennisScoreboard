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
import org.tennisscoreboard.service.PaginationService;
import org.tennisscoreboard.utils.Pagination;
import org.tennisscoreboard.utils.Validation;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "PlayedMatchesServlet", value = "/matches")
public class MatchesServlet extends HttpServlet {
FinishedMatchesPersistenceService persistenceService;
HibernateMatchRepository matchRepository;
HibernatePlayerRepository playerRepository;
PaginationService paginationService;
Pagination pagination;

    public void init() {
        matchRepository = new HibernateMatchRepository();
        playerRepository = new HibernatePlayerRepository();
        paginationService = new PaginationService(matchRepository);
        persistenceService = new FinishedMatchesPersistenceService(matchRepository);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<MatchApiDto> matches;
        String pathName="";
        int pageNumber = parseInt(request.getParameter("page"));
        String playerName = request.getParameter("filter_by_player_name");
        String errorMessage = "No matches found";
        if(!Validation.isName(playerName)) {
            request.setAttribute("message", errorMessage);
            playerName = null;
        }
        if(playerName==null || playerName.isBlank()) {
            pagination = paginationService.getPagination(pageNumber);
            matches = persistenceService.getMatches(pageNumber-1);
        } else {
            pagination = paginationService.getPagination(pageNumber, playerName);
            matches = persistenceService.getMatchesByPlayerName(playerName, pageNumber-1);
        }
        if(request.getParameter("filter_by_player_name")!=null) {
            pathName="&filter_by_player_name="+request.getParameter("filter_by_player_name");
        }
        request.setAttribute("pathName", pathName);
        request.setAttribute("pagination", pagination);
        request.setAttribute("matches", matches);
        request.getRequestDispatcher("/matches.jsp").forward(request, response);
    }

    private int parseInt(String page){
        int pageNumber;
        try{
            pageNumber = Integer.parseInt(page);
        } catch (NumberFormatException e) {
            pageNumber = 1;
        }
        if(pageNumber < 1){
            pageNumber = 1;
        }
        return pageNumber;
    }
}