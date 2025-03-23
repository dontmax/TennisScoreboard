package org.tennisscoreboard.servlets;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.tennisscoreboard.db.DataLoader;
import org.tennisscoreboard.repository.HibernateMatchRepository;
import org.tennisscoreboard.repository.HibernatePlayerRepository;

@WebListener
public class DbInitializer implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        HibernateMatchRepository matchRepository = new HibernateMatchRepository();
        HibernatePlayerRepository playerRepository = new HibernatePlayerRepository();
        DataLoader loader = new DataLoader(playerRepository, matchRepository);
        loader.loadData();
    }
}
