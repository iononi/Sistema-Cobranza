package dao;

import java.util.List;

public interface Crud<T> {
    boolean insert (T entity); // create
    T getById (long id); // read
    T get (T entity); // read

    List<T> getAll (); // read

    boolean update (T entity); // update
    boolean delete (T entity); // delete
}
