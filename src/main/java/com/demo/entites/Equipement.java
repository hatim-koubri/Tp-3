package com.demo.entites;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Equipement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identifiant de l'équipement

    @Column(nullable = false, unique = true)
    private String nom; // Nom unique de l'équipement

    @ManyToMany(mappedBy = "equipements")
    private Set<Salle> salles = new HashSet<>(); // Salles contenant cet équipement

    public Equipement() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Set<Salle> getSalles() {
        return salles;
    }

    public void setSalles(Set<Salle> salles) {
        this.salles = salles;
    }
}
