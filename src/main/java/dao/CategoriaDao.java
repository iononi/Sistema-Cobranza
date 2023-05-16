package dao;

import models.Categoria;

public class CategoriaDao implements Crud<Categoria> {
    @Override
    public boolean insert () {
        return false;
    }

    @Override
    public Categoria getById (long id) {
        return null;
    }

    @Override
    public Categoria get (Categoria entity) {
        return null;
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
