package org.tennisscoreboard.servlets;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.tennisscoreboard.repository.MatchRepository;
import org.tennisscoreboard.repository.PlayerRepository;
import org.tennisscoreboard.service.CurrentMatchesService;
import org.tennisscoreboard.service.FinishedMatchesPersistenceService;
import org.tennisscoreboard.service.PaginationService;

@WebListener
public class ApplicationInitializer implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        CurrentMatchesService currentMatchesService = CurrentMatchesService.getInstance();
        MatchRepository matchRepository = new MatchRepository();
        PlayerRepository playerRepository = new PlayerRepository();
        FinishedMatchesPersistenceService persistenceService = new FinishedMatchesPersistenceService(matchRepository, playerRepository);
        PaginationService paginationService = new PaginationService(matchRepository);

        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("currentMatchesService", currentMatchesService);
        servletContext.setAttribute("persistenceService", persistenceService);
        servletContext.setAttribute("paginationService", paginationService);
    }

}
