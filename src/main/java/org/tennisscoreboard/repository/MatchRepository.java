package org.tennisscoreboard.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.tennisscoreboard.entity.Match;
import org.tennisscoreboard.utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class MatchRepository {

    public void save(Session session, Match match) {
        try {
            session.persist(match);
        } catch (Exception e) {
            throw new HibernateException("Error adding new match " + match);
        }
    }

    public List<Match> getMatchesByPlayerName(String playerName, int pageNumber, int tableSize) {
        List<Match> matches = new ArrayList<Match>();
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            matches = session.createQuery("FROM Match m " +
                            "WHERE m.firstPlayer.name LIKE :playerName OR m.secondPlayer.name LIKE :playerName order by m.id desc", Match.class)
                    .setParameter("playerName", "%" + playerName + "%")
                    .setFirstResult(pageNumber * tableSize)
                    .setMaxResults(tableSize)
                    .getResultList();
            transaction.commit();
        } catch (Exception e) {
            throw new HibernateException("Error getting matches");
        }
        return matches;
    }

    public List<Match> getMatches(int pageNumber, int tableSize) {
        List<Match> matches = new ArrayList<>();
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            matches = session.createQuery("from Match m ORDER BY m.id desc", Match.class)
                    .setFirstResult(pageNumber * tableSize)
                    .setMaxResults(tableSize).getResultList();
            transaction.commit();
        } catch (Exception e) {
            throw new HibernateException("Error getting matches");
        }
        return matches;
    }

    public long getTotalMatchCount() {
        Long matchCount = 0L;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            matchCount = (Long) session.createQuery("select count(*) from Match")
                    .uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            throw new HibernateException("Error getting total matchCount");
        }
        return (matchCount == null) ? 0 : matchCount;
    }

    public long getTotalMatchCountByPlayerName(String playerName) {
        Long matchCount = 0L;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            matchCount = (Long) session.createQuery("select count(*) from Match where firstPlayer.name LIKE :playerName or secondPlayer.name LIKE :playerName")
                    .setParameter("playerName", "%" + playerName + "%")
                    .uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            throw new HibernateException("Error getting total MatchCount by player name");
        }
        return (matchCount == null) ? 0 : matchCount;
    }

}
