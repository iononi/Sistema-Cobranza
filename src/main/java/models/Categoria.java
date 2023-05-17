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
    private String categoria;

    public Categoria () {
    }

    public Categoria (long categoriaID, String categoia) {
        this.categoriaID = categoriaID;
        this.categoria = categoia;
    }

    public Categoria (String categoria) {
        this.categoria = categoria;
    }

    public long getCategoriaID () {
        return categoriaID;
    }

    public void setCategoriaID (long categoriaID) {
        this.categoriaID = categoriaID;
    }

    public String getCategoria () {
        return categoria;
    }

    public void setCategoria (String categoria) {
        this.categoria = categoria;
    }
}
