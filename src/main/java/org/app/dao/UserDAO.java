package org.app.dao;

import org.app.model.User;
import org.app.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.mindrot.jbcrypt.BCrypt;

public class UserDAO {

    public boolean register(User user) {
        Transaction tx = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            String hql = "FROM User WHERE username = :u";
            User userExists = session.createQuery(hql, User.class)
                    .setParameter("u", user.getUsername())
                    .uniqueResult();

            if (userExists != null) {
                throw new Exception("User already exists");
            }

            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            user.setPassword(hashedPassword);

            session.persist(user);

            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public User validateUser(String username, String password) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String hql = "FROM User WHERE username = :u";

            User user = session.createQuery(hql, User.class)
                    .setParameter("u", username)
                    .uniqueResult();

            if(user != null && BCrypt.checkpw(password, user.getPassword())) {
                return user;
            }

            return null;
        }
    }
}