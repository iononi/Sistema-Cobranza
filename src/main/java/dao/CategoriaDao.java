package dao;

import models.Categoria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.service.UnknownServiceException;
import utils.HibernateUtil;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;

public class CategoriaDao implements Crud<Categoria> {

    private final SessionFactory sessionFactory;

    public CategoriaDao (SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean insert (Categoria entity) {

        boolean isInserted = false;
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession ()) {

            transaction = session.beginTransaction ();

            session.persist (entity);

            transaction.commit ();

            isInserted = true;

        } catch (UnknownServiceException ex) {
            if (transaction != null) {
                transaction.rollback ();
            }
            Logger.getLogger(CategoriaDao.class.getName ()).log(Level.SEVERE, ex.getMessage (), ex);
        }
        return isInserted;
    }

    @Override
    public Optional<Categoria> getById (long id) {

        Categoria category = new Categoria ();

        try (Session session = sessionFactory.openSession ()) {
            category = session.get (Categoria.class, id);

        } catch (UnknownServiceException ex) {
            Logger.getLogger (CategoriaDao.class.getName ()).log (Level.SEVERE, ex.getMessage (), ex);
        }
        return Optional.ofNullable (category);
    }

    @Override
    public Optional<Categoria> get (Categoria entity) {
        Categoria c;
        try (Session session = sessionFactory.openSession ()) {
            c = session.get (Categoria.class, entity);
        }
        return Optional.ofNullable (c);
    }

    @Override
    public List<Categoria> getAll () {

        List<Categoria> categoryList = null;
        try (Session session = sessionFactory.openSession ()) {

            Query<Categoria> query = session.createQuery ("from models.Categoria", Categoria.class);

            categoryList = query.list ();

        } catch (HibernateException ex) {
            Logger.getLogger (CategoriaDao.class.getName ()).log (Level.SEVERE, ex.getMessage(), ex);
        }

        return categoryList;
    }

    @Override
    public boolean update (Categoria entity) {

        boolean isUpdated = false;
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession () ) {

            transaction = session.beginTransaction ();

            Categoria category = session.get (Categoria.class, entity.getCategoriaID ());

            if (category != null) {
                session.update (entity);

                transaction.commit ();

                isUpdated = true;
            }

        } catch (HibernateException | IllegalStateException ex) {
            if (transaction != null) {
                transaction.rollback ();
            }
            Logger.getLogger(CategoriaDao.class.getName ()).log(Level.SEVERE, ex.getMessage (), ex);
        }

        return isUpdated;
    }

    @Override
    public boolean delete (Categoria entity) {

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
            Logger.getLogger (CategoriaDao.class.getName ()).log (Level.SEVERE, ex.getMessage (), ex);
        }
        return isDeleted;
    }
}
