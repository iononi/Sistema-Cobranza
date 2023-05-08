package models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "Productos")
public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductoID", nullable = false, unique = true)
    private long productoID;

    @Column(name = "Nombre", length = 128, nullable = false)
    private String nombre;

    @Column(name = "Descripion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "Stock", nullable = false)
    private int stock;

    @Column(name = "Precio", nullable = false)
    private double precio;

    @ManyToOne
    @JoinColumn(name = "FK_ProveedorID", nullable = false, foreignKey = @ForeignKey(name = "fk_proveedorId"), referencedColumnName = "ProveedorID")
    private Proveedor fkProveedorId;

    @ManyToOne
    @JoinColumn(name = "FK_CategoriaID", nullable = false, foreignKey = @ForeignKey(name = "fk_categoriaId"), referencedColumnName = "CategoriaID")
    private Categoria fkCategoriaId;

    public Producto () {
    }

    public Producto (long productoID, String nombre, String descripcion, int stock, double precio, Proveedor fkProveedorId, Categoria fkCategoriaId) {
        this.productoID = productoID;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.stock = stock;
        this.precio = precio;
        this.fkProveedorId = fkProveedorId;
        this.fkCategoriaId = fkCategoriaId;
    }

    public Producto (String nombre, String descripcion, int stock, double precio, Proveedor fkProveedorId, Categoria fkCategoriaId) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.stock = stock;
        this.precio = precio;
        this.fkProveedorId = fkProveedorId;
        this.fkCategoriaId = fkCategoriaId;
    }

    public long getProductoID () {
        return productoID;
    }

    public String getNombre () {
        return nombre;
    }

    public String getDescripcion () {
        return descripcion;
    }

    public int getStock () {
        return stock;
    }

    public double getPrecio () {
        return precio;
    }

    public Proveedor getFkProveedorId () {
        return fkProveedorId;
    }

    public Categoria getFkCategoriaId () {
        return fkCategoriaId;
    }
}
