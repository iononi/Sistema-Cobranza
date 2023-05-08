package models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Ventas")
public class Venta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VentaID" ,nullable = false, unique = true)
    private long ventaID;

    @ManyToOne
    @JoinColumn(name = "FK_EmpleadoID", nullable = false, foreignKey = @ForeignKey(name = "fk_empleadoId"), referencedColumnName = "EmpleadoID")
    private Empleado fkEmpleadoID;

    @Column(name = "Fecha", nullable = false)
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "FK_MetodoPagoID", nullable = false, foreignKey = @ForeignKey(name = "fk_metodoId"), referencedColumnName = "MetodoPagoID")
    private MetodoPago fkMetodoPago;

    public Venta () {
    }

    public Venta (long ventaID, Empleado fkEmpleadoID, Date fecha, MetodoPago fkMetodoPago) {
        this.ventaID = ventaID;
        this.fkEmpleadoID = fkEmpleadoID;
        this.fecha = fecha;
        this.fkMetodoPago = fkMetodoPago;
    }

    public Venta (Empleado fkEmpleadoID, Date fecha, MetodoPago fkMetodoPago) {
        this.fkEmpleadoID = fkEmpleadoID;
        this.fecha = fecha;
        this.fkMetodoPago = fkMetodoPago;
    }

    public long getVentaID () {
        return ventaID;
    }

    public void setVentaID (long ventaID) {
        this.ventaID = ventaID;
    }

    public Empleado getFkEmpleadoID () {
        return fkEmpleadoID;
    }

    public void setFkEmpleadoID (Empleado fkEmpleadoID) {
        this.fkEmpleadoID = fkEmpleadoID;
    }

    public Date getFecha () {
        return fecha;
    }

    public void setFecha (Date fecha) {
        this.fecha = fecha;
    }

    public MetodoPago getFkMetodoPago () {
        return fkMetodoPago;
    }

    public void setFkMetodoPago (MetodoPago fkMetodoPago) {
        this.fkMetodoPago = fkMetodoPago;
    }
}
