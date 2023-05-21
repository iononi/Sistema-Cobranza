package models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table (name = "Empleados")
public class Empleado implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "EmpleadoID", nullable = false, unique = true)
    private long empleadoID;

    @Column (name = "Nombre", length = 50, nullable = false)
    private String nombre;

    @Column (name = "ApellidoPaterno", nullable = false, length = 50)
    private String apellidoPaterno;

    @Column (name = "ApellidoMaterno", length = 50)
    private String apellidoMaterno;

    @Column (name = "CURP", nullable = false, unique = true)
    private String curp;

    @Column (name = "RFC", nullable = false, unique = true)
    private String rfc;

    @Column (name = "contrasenia", nullable = false)
    private int contrasenia;

    @Column (name = "Salario", nullable = false)
    private double salario;

    @Column (name = "Telefono", length = 15)
    private String telefono;

    public Empleado () {
    }

    public Empleado (int empleadoID, String nombre, String apellidoPaterno, String apellidoMaterno, String curp,
                    String rfc, double salario, String telefono) {
        this.empleadoID = empleadoID;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.curp = curp;
        this.rfc = rfc;
        this.salario = salario;
        this.telefono = telefono;
    }

    public Empleado (String nombre, String apellidoPaterno, String apellidoMaterno, String curp, String rfc, double salario, String telefono) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.curp = curp;
        this.rfc = rfc;
        this.salario = salario;
        this.telefono = telefono;
    }

    public long getEmpleadoID () {
        return empleadoID;
    }

    public void setEmpleadoID (int empleadoID) {
        this.empleadoID = empleadoID;
    }

    public String getNombre () {
        return nombre;
    }

    public void setNombre (String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno () {
        return apellidoPaterno;
    }

    public void setApellidoPaterno (String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno () {
        return apellidoMaterno;
    }

    public void setApellidoMaterno (String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getCurp () {
        return curp;
    }

    public void setCurp (String curp) {
        this.curp = curp;
    }

    public String getRfc () {
        return rfc;
    }

    public void setRfc (String rfc) {
        this.rfc = rfc;
    }

    public int getContrasenia () {
        return this.contrasenia;
    }

    public void setContrasenia (String password) {
        this.contrasenia = password.hashCode ();
    }

    public double getSalario () {
        return salario;
    }

    public void setSalario (double salario) {
        this.salario = salario;
    }

    public String getTelefono () {
        return telefono;
    }

    public void setTelefono (String telefono) {
        this.telefono = telefono;
    }
}
