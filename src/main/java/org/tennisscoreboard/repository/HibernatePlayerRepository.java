package org.tennisscoreboard.repository;

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
            e.printStackTrace();
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
            e.printStackTrace();
            return null;
        }
    }

    public Player getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query<Player> query = session.createQuery("from Player where id = :id", Player.class);
            query.setParameter("id", id);
            Player player;
            player = query.getSingleResult();
            return player;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}