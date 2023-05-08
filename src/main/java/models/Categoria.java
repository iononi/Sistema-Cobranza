package models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "Categorias")
public class Categoria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoriaID", nullable = false, unique = true)
    private long categoriaID;

    @Column(name = "Categoria", length = 50, nullable = false)
    private String categoia;

    public Categoria () {
    }

    public Categoria (long categoriaID, String categoia) {
        this.categoriaID = categoriaID;
        this.categoia = categoia;
    }

    public Categoria (String categoia) {
        this.categoia = categoia;
    }

    public long getCategoriaID () {
        return categoriaID;
    }

    public void setCategoriaID (long categoriaID) {
        this.categoriaID = categoriaID;
    }

    public String getCategoia () {
        return categoia;
    }

    public void setCategoia (String categoia) {
        this.categoia = categoia;
    }
}
