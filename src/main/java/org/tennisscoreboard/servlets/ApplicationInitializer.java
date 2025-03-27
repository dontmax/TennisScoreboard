package org.tennisscoreboard.servlets;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.tennisscoreboard.repository.HibernateMatchRepository;
import org.tennisscoreboard.service.FinishedMatchesPersistenceService;
import org.tennisscoreboard.service.PaginationService;

@WebListener
public class ApplicationInitializer implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        HibernateMatchRepository matchRepository = new HibernateMatchRepository();
        FinishedMatchesPersistenceService persistenceService = new FinishedMatchesPersistenceService(matchRepository);
        PaginationService paginationService = new PaginationService(matchRepository);

        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("persistenceService", persistenceService);
        servletContext.setAttribute("paginationService", paginationService);
    }

}
