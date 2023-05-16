package dao;

public interface Crud<T> {
    boolean insert (); // create
    T getById (long id); // read
    T get (T entity); // read
    boolean update (T entity); // update
    boolean delete (T entity); // delete
}
