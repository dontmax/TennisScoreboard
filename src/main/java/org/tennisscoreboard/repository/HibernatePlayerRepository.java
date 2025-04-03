package org.tennisscoreboard.repository;

import jakarta.persistence.NoResultException;
import jakarta.validation.ConstraintViolationException;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.tennisscoreboard.entity.Player;
import org.hibernate.Session;

import java.util.Optional;

public class HibernatePlayerRepository {

    public void save(Session session, Player player) {
        try {
            session.persist(player);
        } catch (Exception e) {
            if (!(e instanceof ConstraintViolationException)) {
                throw new HibernateException("Error saving player " + player.getName());
            }
        }
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