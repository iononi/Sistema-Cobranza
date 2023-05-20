package dao;

import models.MetodoPago;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.HibernateException;

public class MetodoPagoDao implements Crud<MetodoPago> {

    private final SessionFactory sessionFactory;

    public MetodoPagoDao (SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean insert (MetodoPago entity) {
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
            Logger.getLogger (MetodoPagoDao.class.getName ()).log (Level.SEVERE, ex.getMessage (), ex);
        }
        return isInserted;
    }

    @Override
    public Optional<MetodoPago> getById (long id) {
        MetodoPago paymentMethod = new MetodoPago ();

        try (Session session = sessionFactory.openSession ()) {
            paymentMethod = session.get (MetodoPago.class, id);

        } catch (HibernateException ex) {
            Logger.getLogger (MetodoPagoDao.class.getName ()).log (Level.SEVERE, ex.getMessage (), ex);
        }
        return Optional.ofNullable (paymentMethod);
    }

    @Override
    public Optional<MetodoPago> get (MetodoPago entity) {
        MetodoPago paymentMethod;
        try (Session session = sessionFactory.openSession ()) {
            paymentMethod = session.get (MetodoPago.class, entity);
        }
        return Optional.ofNullable (paymentMethod);
    }

    @Override
    public List<MetodoPago> getAll () {
        List<MetodoPago> paymentMethodList = null;
        try (Session session = sessionFactory.openSession ()) {

            paymentMethodList = session.createQuery ("from models.MetodoPago", MetodoPago.class).getResultList ();

        } catch (HibernateException ex) {
            Logger.getLogger (MetodoPagoDao.class.getName ()).log (Level.SEVERE, ex.getMessage (), ex);
        }

        return paymentMethodList;
    }

    @Override
    public boolean update (MetodoPago entity) {
        boolean isUpdated = false;
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession ()) {

            transaction = session.beginTransaction ();

            MetodoPago paymentMethod = session.get (MetodoPago.class, entity.getMetodoPagoID ());

            if (paymentMethod != null) {
                session.update (entity);

                transaction.commit ();

                isUpdated = true;
            }

        } catch (HibernateException | IllegalStateException ex) {
            if (transaction != null) {
                transaction.rollback ();
            }
            Logger.getLogger (MetodoPagoDao.class.getName ()).log (Level.SEVERE, ex.getMessage (), ex);
        }

        return isUpdated;
    }

    @Override
    public boolean delete (MetodoPago entity) {
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
            Logger.getLogger (MetodoPagoDao.class.getName ()).log (Level.SEVERE, ex.getMessage (), ex);
        }
        return isDeleted;
    }
}
