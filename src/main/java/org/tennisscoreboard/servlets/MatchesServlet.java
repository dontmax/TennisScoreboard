package org.tennisscoreboard.servlets;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.tennisscoreboard.service.FinishedMatchesPersistenceService;
import org.tennisscoreboard.service.PaginationService;
import org.tennisscoreboard.utils.Validation;

import java.io.IOException;

@WebServlet(name = "PlayedMatchesServlet", value = "/matches")
public class MatchesServlet extends HttpServlet {
    FinishedMatchesPersistenceService persistenceService;
    PaginationService paginationService;
    ServletContext servletContext;

    public void init() {
        servletContext = getServletContext();
        paginationService = (PaginationService) servletContext.getAttribute("paginationService");
        persistenceService = (FinishedMatchesPersistenceService) servletContext.getAttribute("persistenceService");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String pathName = "";
        int pageNumber = parseInt(request.getParameter("page"));
        String playerName = request.getParameter("filter_by_player_name");
        if (playerName != null && !Validation.isName(playerName)) {
            playerName = null;
        }
        try {
            if (playerName != null) {
                pathName = "&filter_by_player_name=" + request.getParameter("filter_by_player_name");
            }
            request.setAttribute("pathName", pathName);
            request.setAttribute("pagination", paginationService.getPagination(pageNumber, playerName));
            request.setAttribute("matches", persistenceService.getMatches(pageNumber - 1, playerName));
            request.getRequestDispatcher("/matches.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("message", e.getMessage());
            request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String playerName = request.getParameter("filter_by_player_name");
        String playerNamePath = "";
        if (playerName != null && Validation.isName(playerName)) {
            playerNamePath = "&filter_by_player_name=" + playerName;
        }
        response.sendRedirect(request.getContextPath() + "/matches?page=1" + playerNamePath);
    }

    private int parseInt(String page) {
        int pageNumber;
        try {
            pageNumber = Integer.parseInt(page);
        } catch (NumberFormatException e) {
            pageNumber = 1;
        }
        if (pageNumber < 1) {
            pageNumber = 1;
        }
        return pageNumber;
    }
}