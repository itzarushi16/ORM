package com.lcwd.hiber.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.lcwd.hiber.entities.Student;
import com.lcwd.hiber.entities.Certificate;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            
            // Register annotated entity classes
            configuration.addAnnotatedClass(Student.class);
            configuration.addAnnotatedClass(Certificate.class);

            ServiceRegistry serviceRegistry =
                    new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        } catch (Exception e) {
            throw new RuntimeException("Error creating SessionFactory: " + e.getMessage(), e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
