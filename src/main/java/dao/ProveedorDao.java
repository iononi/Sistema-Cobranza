package dao;

import models.Proveedor;

public class ProveedorDao implements Crud<Proveedor> {
    @Override
    public boolean insert () {
        return false;
    }

    @Override
    public Proveedor getById (long id) {
        return null;
    }

    @Override
    public Proveedor get (Proveedor entity) {
        return null;
    }

    @Override
    public boolean update (Proveedor entity) {
        return false;
    }

    @Override
    public boolean delete (Proveedor entity) {
        return false;
    }
}
