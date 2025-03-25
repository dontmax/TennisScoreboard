package org.tennisscoreboard.repository;

import jakarta.validation.ConstraintViolationException;
import org.hibernate.query.Query;
import org.tennisscoreboard.models.Player;
import org.hibernate.Session;
import org.tennisscoreboard.utils.HibernateUtil;

public class HibernatePlayerRepository {

    public void save(Player player) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(player);
            session.getTransaction().commit();
        } catch (Exception e) {
            if(!(e instanceof ConstraintViolationException)) {
                //throw Database Exception
            }
        }
    }

    public Player getByName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query<Player> query = session.createQuery("from Player where name = :name", Player.class);
            query.setParameter("name", name);
            Player player;
            player = query.getSingleResult();
            return player;
        } catch (Exception e) {
            throw new RuntimeException(e);//throw Database Exception
        }
    }

}