package vn.myclass.core.common.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    private static final SessionFactory sessionFactory = builtSessionFactory();

    private static SessionFactory builtSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();

        } catch (Throwable e) {
            System.out.println("initial session factory fail");
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
