package models;

import jakarta.persistence.*;

import javax.annotation.processing.Generated;
import java.io.Serializable;

@Entity
@Table(name = "MetodosPago")
public class MetodoPago implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MetodoPagoID", nullable = false, unique = true)
    private long metodoPagoID;

    @Column(name = "Metodo", length = 30, nullable = false)
    private String metodo;

    public MetodoPago () {
    }

    public MetodoPago (long metodoPagoID, String metodo) {
        this.metodoPagoID = metodoPagoID;
        this.metodo = metodo;
    }

    public MetodoPago (String metodo) {
        this.metodo = metodo;
    }

    public long getMetodoPagoID () {
        return metodoPagoID;
    }

    public void setMetodoPagoID (long metodoPagoID) {
        this.metodoPagoID = metodoPagoID;
    }

    public String getMetodo () {
        return metodo;
    }

    public void setMetodo (String metodo) {
        this.metodo = metodo;
    }
}
