package org.tennisscoreboard.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.tennisscoreboard.models.Match;
import org.tennisscoreboard.utils.HibernateUtil;

import java.util.List;

public class HibernateMatchRepository {

    public void save(Match match) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.persist(match);
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<Match> getByPlayerName(String playerName, int pageNumber, int tableSize) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            Query<Match> query = session.createQuery("FROM Match m " +
                    "WHERE m.firstPlayer.name = :playerName OR m.secondPlayer.name = :playerName order by m.id desc", Match.class);
            query.setParameter("playerName", playerName);
            int firstResult = pageNumber*5;
            query.setFirstResult(firstResult);
            query.setMaxResults(tableSize);
            List<Match> matches = query.getResultList();
            session.getTransaction().commit();
            return matches;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Match> getMatches(int pageNumber, int tableSize) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            Query<Match> query = session.createQuery("from Match m ORDER BY m.id desc", Match.class);
            int firstResult = pageNumber*5;
            query.setFirstResult(firstResult);
            query.setMaxResults(tableSize);
            List<Match> matches = query.getResultList();
            session.getTransaction().commit();
            return matches;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public long getMatchCount() {
        long matchCount=0;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            Query query = session.createQuery("select count(*) from Match");
            matchCount = (Long)query.uniqueResult();
            session.getTransaction().commit();
            return matchCount;
        } catch (Exception e){
            e.printStackTrace();
        }
        return matchCount;
    }

    public long getMatchCountByPlayerName(String playerName) {
        long matchCount=0;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            Query query = session.createQuery("select count(*) from Match where firstPlayer.name = :playerName or secondPlayer.name = :playerName");
            query.setParameter("playerName", playerName);
            matchCount = (Long)query.uniqueResult();
            session.getTransaction().commit();
            return matchCount;
        } catch (Exception e){
            e.printStackTrace();
        }
        return matchCount;
    }

}
