package dao;

import java.util.List;
import java.util.Optional;

public interface Crud<T> {
    boolean insert (T entity); // create
    Optional<T> getById (long id); // read
    Optional<T> get (T entity); // read

    List<T> getAll (); // read

    boolean update (T entity); // update
    boolean delete (T entity); // delete
}
