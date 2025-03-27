package org.tennisscoreboard.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.tennisscoreboard.models.Match;
import org.tennisscoreboard.utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class HibernateMatchRepository {

    public void save(Match match) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(match);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new HibernateException("Error adding new match " + match);
        }
    }

    public List<Match> getMatchesByPlayerName(String playerName, int pageNumber, int tableSize) {
        List<Match> matches = new ArrayList<Match>();
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<Match> query = session.createQuery("FROM Match m " +
                    "WHERE m.firstPlayer.name LIKE :playerName OR m.secondPlayer.name LIKE :playerName order by m.id desc", Match.class);
            query.setParameter("playerName", playerName + "%");
            int firstResult = pageNumber * 5;
            query.setFirstResult(firstResult);
            query.setMaxResults(tableSize);
            matches = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new HibernateException("Error getting matches");
        }
        return matches;
    }

    public List<Match> getMatches(int pageNumber, int tableSize) {
        List<Match> matches = new ArrayList<>();
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<Match> query = session.createQuery("from Match m ORDER BY m.id desc", Match.class);
            int firstResult = pageNumber * 5;
            query.setFirstResult(firstResult);
            query.setMaxResults(tableSize);
            matches = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new HibernateException("Error getting matches");
        }
        return matches;
    }

    public long getTotalMatchCount() {
        Long matchCount = 0L;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("select count(*) from Match");
            matchCount = (Long) query.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new HibernateException("Error getting total matchCount");
        }
        return (matchCount == null) ? 0 : matchCount;
    }

    public long getTotalMatchCountByPlayerName(String playerName) {
        Long matchCount = 0L;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("select count(*) from Match where firstPlayer.name LIKE :playerName or secondPlayer.name LIKE :playerName");
            query.setParameter("playerName", playerName + "%");
            matchCount = (Long) query.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new HibernateException("Error getting total MatchCount by player name");
        }
        return (matchCount == null) ? 0 : matchCount;
    }

}
