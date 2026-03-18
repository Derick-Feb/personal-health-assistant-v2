package org.app.dao;

import org.app.custom.Exception.InvalidTypeException;
import org.app.model.Activity;
import org.app.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class ActivityDAO {

    public void addActivity(Activity activity) throws Exception {
        InvalidTypeException.checkIfValidType(activity.getType());

        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            session.persist(activity);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public List<Activity> getFilteredActivities(int userId, String type) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String hql = "FROM Activity WHERE userId = :uid";

            if (type != null) {
                hql += " AND type = :type";
            }

            var query = session.createQuery(hql, Activity.class)
                    .setParameter("uid", userId);

            if (type != null) {
                query.setParameter("type", type);
            }

            return query.list();
        }
    }
}