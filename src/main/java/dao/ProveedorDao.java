package dao;

import models.Proveedor;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ProveedorDao implements Crud<Proveedor> {

    private final SessionFactory sessionFactory;

    public ProveedorDao (SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean insert (Proveedor entity) {
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
            Logger.getLogger (ProveedorDao.class.getName ()).log (Level.SEVERE, ex.getMessage (), ex);
        }
        return isInserted;
    }

    @Override
    public Optional<Proveedor> getById (long id) {
        Proveedor supplier = new Proveedor ();

        try (Session session = sessionFactory.openSession ()) {
            supplier = session.get (Proveedor.class, id);

        } catch (HibernateException ex) {
            Logger.getLogger (ProveedorDao.class.getName ()).log (Level.SEVERE, ex.getMessage (), ex);
        }
        return Optional.ofNullable (supplier);
    }

    @Override
    public Optional<Proveedor> get (Proveedor entity) {
        Proveedor supplier;
        try (Session session = sessionFactory.openSession ()) {
            supplier = session.get (Proveedor.class, entity);
        }
        return Optional.ofNullable (supplier);
    }

    @Override
    public List<Proveedor> getAll() {
        List<Proveedor> suppliersList = null;
        try (Session session = sessionFactory.openSession ()) {

            suppliersList = session.createQuery ("from models.Proveedor", Proveedor.class).getResultList ();

        } catch (HibernateException ex) {
            Logger.getLogger (ProveedorDao.class.getName ()).log (Level.SEVERE, ex.getMessage (), ex);
        }

        return suppliersList;
    }

    @Override
    public boolean update (Proveedor entity) {
        boolean isUpdated = false;
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession ()) {

            transaction = session.beginTransaction ();

            Proveedor supplier = session.get (Proveedor.class, entity.getProveedorID ());

            if (supplier != null) {
                session.update (entity);

                transaction.commit ();

                isUpdated = true;
            }

        } catch (HibernateException | IllegalStateException ex) {
            if (transaction != null) {
                transaction.rollback ();
            }
            Logger.getLogger (ProveedorDao.class.getName ()).log (Level.SEVERE, ex.getMessage (), ex);
        }

        return isUpdated;
    }

    @Override
    public boolean delete (Proveedor entity) {
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
            Logger.getLogger (ProveedorDao.class.getName ()).log (Level.SEVERE, ex.getMessage (), ex);
        }
        return isDeleted;
    }
}
