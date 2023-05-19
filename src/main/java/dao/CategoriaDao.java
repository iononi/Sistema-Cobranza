package dao;

import models.Categoria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.service.UnknownServiceException;
import utils.HibernateUtil;
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

        Transaction transaction = null;

        try (Session session = sessionFactory.openSession ()) {

            transaction = session.beginTransaction ();

            session.persist (entity);

            transaction.commit ();

        } catch (UnknownServiceException ex) {
            if (transaction != null) {
                transaction.rollback ();
            }
            Logger.getLogger(CategoriaDao.class.getName ()).log(Level.SEVERE, ex.getMessage (), ex);
            return false;
        }
        return true;
    }

    @Override
    public Categoria getById (long id) {

        Session session = sessionFactory.openSession ();

        Categoria category = new Categoria ();

        try {
            category = session.get (Categoria.class, id);

        } catch (UnknownServiceException ex) {
            Logger.getLogger (CategoriaDao.class.getName ()).log (Level.SEVERE, ex.getMessage (), ex);
        } finally {
            session.close ();
        }
        return category;
    }

    @Override
    public Categoria get (Categoria entity) {
        Categoria c;
        try (Session session = HibernateUtil.getSessionFactory ().openSession ()) {
            c = session.get (Categoria.class, entity);
        }
        return c;
    }

    @Override
    public List<Categoria> getAll () {

        Session session = sessionFactory.openSession ();

        Transaction transaction = null;
        List<Categoria> categoryList = null;
        try {

            transaction = session.beginTransaction ();

            Query<Categoria> query = session.createQuery ("from models.Categoria", Categoria.class);

            categoryList = query.list ();

        } catch (UnknownServiceException ex) {
            if (transaction != null) {
                transaction.rollback ();
            }
            Logger.getLogger (CategoriaDao.class.getName ()).log (Level.SEVERE, ex.getMessage(), ex);
        } finally {
            session.close ();
        }

        return categoryList;
    }

    @Override
    public boolean update (Categoria entity) {

        boolean isUpdated = false;
        Transaction transaction = null;

        try {

            Session session = sessionFactory.openSession ();

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
        Session session = null;
        Transaction transaction = null;

        try {

            session = sessionFactory.openSession ();

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
        } finally {
            if (session != null)
                session.close ();
        }
        return isDeleted;
    }
}
