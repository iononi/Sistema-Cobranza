package dao;

import models.Producto;

public class ProductoDao implements Crud<Producto> {
    @Override
    public boolean insert () {
        return false;
    }

    @Override
    public Producto getById (long id) {
        return null;
    }

    @Override
    public Producto get (Producto entity) {
        return null;
    }

    @Override
    public boolean update (Producto entity) {
        return false;
    }

    @Override
    public boolean delete (Producto entity) {
        return false;
    }
}
