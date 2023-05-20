package dao;

import models.Producto;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ProductoDao implements Crud<Producto> {

    private final SessionFactory sessionFactory;

    public ProductoDao (SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean insert (Producto entity) {
        Transaction transaction = null;

        boolean isInserted = false;

        try (Session session = sessionFactory.openSession ()) {

            transaction = session.beginTransaction ();

            session.persist (entity);

            transaction.commit ();

            isInserted = true;

        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback ();
            }
            Logger.getLogger (ProductoDao.class.getName ()).log (Level.SEVERE, ex.getMessage (), ex);
        }
        return isInserted;
    }

    @Override
    public Optional<Producto> getById (long id) {
        Producto product = new Producto ();

        try (Session session = sessionFactory.openSession ()) {
            product = session.get (Producto.class, id);

        } catch (HibernateException ex) {
            Logger.getLogger (ProductoDao.class.getName ()).log (Level.SEVERE, ex.getMessage (), ex);
        }
        return Optional.ofNullable (product);
    }

    @Override
    public Optional<Producto> get (Producto entity) {
        Producto product;
        try (Session session = sessionFactory.openSession ()) {
            product = session.get (Producto.class, entity);
        }
        return Optional.ofNullable (product);
    }

    @Override
    public List<Producto> getAll() {
        List<Producto> productsList = null;
        try (Session session = sessionFactory.openSession ()) {

            productsList = session.createQuery ("from models.Producto", Producto.class).getResultList ();

        } catch (HibernateException ex) {
            Logger.getLogger (ProductoDao.class.getName ()).log (Level.SEVERE, ex.getMessage (), ex);
        }

        return productsList;
    }

    @Override
    public boolean update (Producto entity) {
        boolean isUpdated = false;
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession ()) {

            transaction = session.beginTransaction ();

            Producto product = session.get (Producto.class, entity.getProductoID ());

            if (product != null) {
                session.update (entity);

                transaction.commit ();

                isUpdated = true;
            }

        } catch (HibernateException | IllegalStateException ex) {
            if (transaction != null) {
                transaction.rollback ();
            }
            Logger.getLogger (ProductoDao.class.getName ()).log (Level.SEVERE, ex.getMessage (), ex);
        }

        return isUpdated;
    }

    @Override
    public boolean delete (Producto entity) {
        boolean isDeleted = false;
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession ()) {

            transaction = session.beginTransaction ();

            if (entity != null) {
                session.remove (entity);
                transaction.commit ();
                isDeleted = true;
            }

        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback ();
            }
            Logger.getLogger (ProductoDao.class.getName ()).log (Level.SEVERE, ex.getMessage (), ex);
        }
        return isDeleted;
    }
}
