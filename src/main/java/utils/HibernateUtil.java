package utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    private static StandardServiceRegistry buildServiceRegistryBuilder () {
        final StandardServiceRegistry registry;
        registry = new StandardServiceRegistryBuilder()
                .configure ()
                .build ();

        return registry;
    }

    private static SessionFactory buildSessionFactory (StandardServiceRegistry registry) {
        return new MetadataSources(registry)
                .buildMetadata ()
                .buildSessionFactory ();
    }

    public static void closeSessionFactory () {
        getSessionFactory ().close ();
    }

    // Singleton design pattern to ensure only one SessionFactory object
    public static SessionFactory getSessionFactory () {
        if (sessionFactory == null) {
            // apply xml configuration and start the sessionFactory
            StandardServiceRegistry registry = buildServiceRegistryBuilder ();
            sessionFactory = buildSessionFactory (registry);
        }

        return sessionFactory;
    }
}
