package org.tennisscoreboard.repository;

import jakarta.validation.ConstraintViolationException;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.tennisscoreboard.models.Player;
import org.hibernate.Session;
import org.tennisscoreboard.utils.HibernateUtil;

public class HibernatePlayerRepository {

    public void save(Player player) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(player);
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            if(!(e instanceof ConstraintViolationException)) {
                throw new HibernateException("Error saving player " + player.getName());
            }
        }
    }

    public Player getByName(String name) {
        Transaction transaction = null;
        Player player;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<Player> query = session.createQuery("from Player where name = :name", Player.class);
            query.setParameter("name", name);
            player = query.getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            throw new HibernateException("Error getting player " + name);
        }
        return player;
    }

}