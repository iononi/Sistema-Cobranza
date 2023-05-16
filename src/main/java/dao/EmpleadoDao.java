package dao;

import models.Empleado;

public class EmpleadoDao implements Crud<Empleado> {
    @Override
    public boolean insert () {
        return false;
    }

    @Override
    public Empleado getById (long id) {
        return null;
    }

    @Override
    public Empleado get (Empleado entity) {
        return null;
    }

    @Override
    public boolean update (Empleado entity) {
        return false;
    }

    @Override
    public boolean delete (Empleado entity) {
        return false;
    }
}
