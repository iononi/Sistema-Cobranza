package dao;

import models.Venta;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.logging.Level;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;


public class VentaDao implements Crud<Venta> {

    private final SessionFactory sessionFactory;

    public VentaDao (SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean insert (Venta entity) {
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
            Logger.getLogger (VentaDao.class.getName ()).log (Level.SEVERE, ex.getMessage (), ex);
        }
        return isInserted;
    }

    @Override
    public Optional<Venta> getById (long id) {
        Venta sale = new Venta ();

        try (Session session = sessionFactory.openSession ()) {
            sale = session.get (Venta.class, id);

        } catch (HibernateException ex) {
            Logger.getLogger (VentaDao.class.getName ()).log (Level.SEVERE, ex.getMessage (), ex);
        }
        return Optional.ofNullable (sale);
    }

    @Override
    public Optional<Venta> get (Venta entity) {
        Venta sale;
        try (Session session = sessionFactory.openSession ()) {
            sale = session.get (Venta.class, entity);
        }
        return Optional.ofNullable (sale);
    }

    @Override
    public List<Venta> getAll() {
        List<Venta> salesList = null;
        try (Session session = sessionFactory.openSession ()) {

            salesList = session.createQuery ("from models.Venta", Venta.class).getResultList ();

        } catch (HibernateException ex) {
            Logger.getLogger (VentaDao.class.getName ()).log (Level.SEVERE, ex.getMessage (), ex);
        }

        return salesList;
    }

    @Override
    public boolean update (Venta entity) {
        boolean isUpdated = false;
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession ()) {

            transaction = session.beginTransaction ();

            Venta sale = session.get (Venta.class, entity.getVentaID ());

            if (sale != null) {
                session.update (entity);

                transaction.commit ();

                isUpdated = true;
            }

        } catch (HibernateException | IllegalStateException ex) {
            if (transaction != null) {
                transaction.rollback ();
            }
            Logger.getLogger (VentaDao.class.getName ()).log (Level.SEVERE, ex.getMessage (), ex);
        }

        return isUpdated;
    }

    @Override
    public boolean delete (Venta entity) {
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
            Logger.getLogger (VentaDao.class.getName ()).log (Level.SEVERE, ex.getMessage (), ex);
        }
        return isDeleted;
    }
}
