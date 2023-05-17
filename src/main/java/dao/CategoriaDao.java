package dao;

import models.Categoria;
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

    private SessionFactory sessionFactory;
    private Session session;

    private Transaction transaction;

    public CategoriaDao () {

    }

    @Override
    public boolean insert (Categoria entity) {

        try {
            sessionFactory = HibernateUtil.getSessionFactory ();

            session = sessionFactory.openSession ();

            transaction = session.beginTransaction ();

            session.persist (entity);

            transaction.commit ();

            session.close ();
            HibernateUtil.closeSessionFactory ();

        } catch (UnknownServiceException ex) {
            Logger.getLogger (CategoriaDao.class.getName ()).log (Level.SEVERE, ex.getMessage(), ex);
        }
        return true;
    }

    @Override
    public Categoria getById (long id) {

        Categoria category = new Categoria ();
        List<Categoria> categoryList;

        try {
            sessionFactory = HibernateUtil.getSessionFactory ();

            session = sessionFactory.openSession ();

            transaction = session.beginTransaction ();

            Query<Categoria> query = session.createNativeQuery ("select * from categorias where categoriaid=:qid", Categoria.class);
            query.setParameter ("qid", id);

            categoryList = query.list ();

            // no category with specified id available
            if (categoryList.isEmpty ()) {
                return null;
            }

            category = categoryList.get (0);


        } catch (UnknownServiceException ex) {
            Logger.getLogger (CategoriaDao.class.getName ()).log (Level.SEVERE, ex.getMessage(), ex);
        } finally {
            session.close ();
            HibernateUtil.closeSessionFactory ();
        }
        return category;
    }

    @Override
    public Categoria get (Categoria entity) {
        return null;
    }

    @Override
    public List<Categoria> getAll () {
        List<Categoria> categoryList = null;
        try {
            sessionFactory = HibernateUtil.getSessionFactory ();

            session = sessionFactory.openSession ();

            transaction = session.beginTransaction ();

            Query<Categoria> query = session.createQuery ("from models.Categoria", Categoria.class);

            categoryList = query.list ();

        } catch (UnknownServiceException ex) {
            Logger.getLogger (CategoriaDao.class.getName ()).log (Level.SEVERE, ex.getMessage(), ex);
        } finally {
            session.close ();
            HibernateUtil.closeSessionFactory ();
        }

        return categoryList;
    }

    @Override
    public boolean update (Categoria entity) {
        return false;
    }

    @Override
    public boolean delete (Categoria entity) {
        return false;
    }
}
