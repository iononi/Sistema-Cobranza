package dao;

import models.Empleado;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class EmpleadoDao implements Crud<Empleado> {

    private final SessionFactory sessionFactory;

    public EmpleadoDao (SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean insert(Empleado entity) {
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
            Logger.getLogger (EmpleadoDao.class.getName ()).log (Level.SEVERE, ex.getMessage (), ex);
        }
        return isInserted;
    }

    @Override
    public Optional<Empleado> getById (long id) {
        Empleado employee = new Empleado ();

        try (Session session = sessionFactory.openSession ()) {
            employee = session.get (Empleado.class, id);

        } catch (HibernateException ex) {
            Logger.getLogger (EmpleadoDao.class.getName ()).log (Level.SEVERE, ex.getMessage (), ex);
        }
        return Optional.ofNullable (employee);
    }

    @Override
    public Optional<Empleado> get (Empleado entity) {
        Empleado employee;
        try (Session session = sessionFactory.openSession ()) {
            employee = session.get (Empleado.class, entity);
        }
        return Optional.ofNullable (employee);
    }
    @Override
    public List<Empleado> getAll() {
        List<Empleado> employeesList = null;
        try (Session session = sessionFactory.openSession ()) {

            employeesList = session.createQuery ("from models.Empleado", Empleado.class).getResultList ();

        } catch (HibernateException ex) {
            Logger.getLogger (EmpleadoDao.class.getName ()).log (Level.SEVERE, ex.getMessage (), ex);
        }

        return employeesList;
    }

    @Override
    public boolean update (Empleado entity) {
        boolean isUpdated = false;
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession ()) {

            transaction = session.beginTransaction ();

            Empleado employee = session.get (Empleado.class, entity.getEmpleadoID ());

            if (employee != null) {
                session.update (entity);

                transaction.commit ();

                isUpdated = true;
            }

        } catch (HibernateException | IllegalStateException ex) {
            if (transaction != null) {
                transaction.rollback ();
            }
            Logger.getLogger (EmpleadoDao.class.getName ()).log (Level.SEVERE, ex.getMessage (), ex);
        }

        return isUpdated;
    }

    @Override
    public boolean delete (Empleado entity) {
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
            Logger.getLogger (EmpleadoDao.class.getName ()).log (Level.SEVERE, ex.getMessage (), ex);
        }
        return isDeleted;
    }
}
