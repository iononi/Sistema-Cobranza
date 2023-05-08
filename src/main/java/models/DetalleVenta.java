package models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "DetallesVenta")
public class DetalleVenta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DetallesVentaID", nullable = false, unique = true)
    private long detallesVentaID;

    @ManyToOne
    @JoinColumn(name = "FK_VentaID", nullable = false, foreignKey = @ForeignKey(name = "fk_ventaId"), referencedColumnName = "VentaID")
    private Venta fkVentaID;

    @ManyToOne
    @JoinColumn(name = "FK_ProductoID", nullable = false, foreignKey = @ForeignKey(name = "fk_productoId"), referencedColumnName = "ProductoID")
    private Producto fkProductoID;

    @Column(name = "Cantidad", nullable = false)
    private int cantidad;

    public DetalleVenta () {
    }

    public DetalleVenta (long detallesVentaID, Venta fkVentaID, Producto fkProductoID, int cantidad) {
        this.detallesVentaID = detallesVentaID;
        this.fkVentaID = fkVentaID;
        this.fkProductoID = fkProductoID;
        this.cantidad = cantidad;
    }

    public DetalleVenta (Venta fkVentaID, Producto fkProductoID, int cantidad) {
        this.fkVentaID = fkVentaID;
        this.fkProductoID = fkProductoID;
        this.cantidad = cantidad;
    }

    public long getDetallesVentaID () {
        return detallesVentaID;
    }

    public void setDetallesVentaID (long detallesVentaID) {
        this.detallesVentaID = detallesVentaID;
    }

    public Venta getFkVentaID () {
        return fkVentaID;
    }

    public void setFkVentaID (Venta fkVentaID) {
        this.fkVentaID = fkVentaID;
    }

    public Producto getFkProductoID () {
        return fkProductoID;
    }

    public void setFkProductoID (Producto fkProductoID) {
        this.fkProductoID = fkProductoID;
    }

    public int getCantidad () {
        return cantidad;
    }

    public void setCantidad (int cantidad) {
        this.cantidad = cantidad;
    }
}
