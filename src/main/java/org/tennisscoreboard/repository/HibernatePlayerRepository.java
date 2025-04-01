package org.tennisscoreboard.repository;

import jakarta.persistence.NoResultException;
import jakarta.validation.ConstraintViolationException;
import org.hibernate.HibernateException;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.tennisscoreboard.models.Player;
import org.hibernate.Session;
import org.tennisscoreboard.utils.HibernateUtil;

import java.util.Optional;

public class HibernatePlayerRepository {

    public void save(Player player) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(player);
            transaction.commit();
        } catch (Exception e) {
            if (!(e instanceof ConstraintViolationException)) {
                throw new HibernateException("Error saving player " + player.getName());
            }
            if (transaction != null) {
                transaction.rollback();
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
            throw new HibernateException("Error getting player " + name);
        }
        return player;
    }

    public Optional<Player> getByName(Session session, String name) {
        Player player;
        try {
            Query<Player> query = session.createQuery("from Player where name = :name", Player.class);
            query.setParameter("name", name);
            player = query.getSingleResult();
        } catch (NoResultException e) {
            return Optional.empty();
        } catch (Exception e) {
            throw new HibernateException("Database failure on getting player with name " + name, e);
        }
        return Optional.ofNullable(player);
    }

}