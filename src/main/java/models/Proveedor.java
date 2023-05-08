package models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "Proveedores")
public class Proveedor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProveedorID", nullable = false, unique = true)
    private long proveedorID;

    @Column(name = "Nombre", length = 80, nullable = false)
    private String nombre;

    public Proveedor() {
    }

    public Proveedor (long proveedorID, String nombre) {
        this.proveedorID = proveedorID;
        this.nombre = nombre;
    }

    public Proveedor (String nombre) {
        this.nombre = nombre;
    }

    public long getProveedorID () {
        return proveedorID;
    }

    public void setProveedorID (long proveedorID) {
        this.proveedorID = proveedorID;
    }

    public String getNombre () {
        return nombre;
    }

    public void setNombre (String nombre) {
        this.nombre = nombre;
    }
}
