package dao;

import models.Categoria;
import models.DetalleVenta;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.service.UnknownServiceException;
import utils.HibernateUtil;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DetalleVentaDao implements Crud<DetalleVenta> {

    private final SessionFactory sessionFactory;

    public DetalleVentaDao (SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean insert (DetalleVenta entity) {
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
            Logger.getLogger(DetalleVentaDao.class.getName ()).log(Level.SEVERE, ex.getMessage (), ex);
        }
        return isInserted;
    }

    @Override
    public Optional<DetalleVenta> getById (long id) {

        DetalleVenta orderDetails = new DetalleVenta ();

        try (Session session = sessionFactory.openSession ()) {
            orderDetails = session.get (DetalleVenta.class, id);

        } catch (HibernateException ex) {
            Logger.getLogger (DetalleVentaDao.class.getName ()).log (Level.SEVERE, ex.getMessage (), ex);
        }
        return Optional.ofNullable (orderDetails);
    }

    @Override
    public Optional<DetalleVenta> get (DetalleVenta entity) {
        DetalleVenta order;
        try (Session session = sessionFactory.openSession ()) {
            order = session.get (DetalleVenta.class, entity);
        }
        return Optional.ofNullable (order);
    }
    @Override
    public List<DetalleVenta> getAll () {

        List<DetalleVenta> orderDetailsList = null;
        try (Session session = sessionFactory.openSession ()) {

            orderDetailsList = session.createQuery ("from models.DetalleVenta", DetalleVenta.class).getResultList ();

        } catch (HibernateException ex) {
            Logger.getLogger (DetalleVentaDao.class.getName ()).log (Level.SEVERE, ex.getMessage(), ex);
        }

        return orderDetailsList;
    }

    @Override
    public boolean update (DetalleVenta entity) {
        boolean isUpdated = false;
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession ()) {

            transaction = session.beginTransaction ();

            DetalleVenta orderDetails = session.get (DetalleVenta.class, entity.getDetallesVentaID ());

            if (orderDetails != null) {
                session.update (entity);

                transaction.commit ();

                isUpdated = true;
            }

        } catch (HibernateException | IllegalStateException ex) {
            if (transaction != null) {
                transaction.rollback ();
            }
            Logger.getLogger(DetalleVentaDao.class.getName ()).log(Level.SEVERE, ex.getMessage (), ex);
        }

        return isUpdated;
    }

    @Override
    public boolean delete (DetalleVenta entity) {
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
            Logger.getLogger (DetalleVentaDao.class.getName ()).log (Level.SEVERE, ex.getMessage (), ex);
        }
        return isDeleted;
    }
}
