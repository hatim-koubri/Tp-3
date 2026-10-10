package com.demo.entites;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Salle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identifiant de la salle

    @Column(nullable = false)
    private String nom; // Nom de la salle

    @OneToMany(mappedBy = "salle", orphanRemoval = true)
    private Set<Reservation> reservations = new HashSet<>(); // Réservations de cette salle

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "salle_equipement",
        joinColumns = @JoinColumn(name = "salle_id"),
        inverseJoinColumns = @JoinColumn(name = "equipement_id")
    )
    private Set<Equipement> equipements = new HashSet<>(); // Équipements présents dans la salle

    public Salle() {
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation); // Ajoute la réservation à la salle
        reservation.setSalle(this); // Associe la réservation à cette salle
    }

    public void addEquipement(Equipement equipement) {
        equipements.add(equipement); // Ajoute l'équipement à la salle
        equipement.getSalles().add(this); // Met à jour l'autre côté de la relation
    }

    public void removeEquipement(Equipement equipement) {
        equipements.remove(equipement); // Retire l'équipement de la salle
        equipement.getSalles().remove(this); // Met à jour l'autre côté de la relation
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

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Set<Equipement> getEquipements() {
        return equipements;
    }

    public void setEquipements(Set<Equipement> equipements) {
        this.equipements = equipements;
    }
}
