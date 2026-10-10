package com.demo.entites;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identifiant de l'utilisateur

    @Column(nullable = false, unique = true)
    private String email; // Adresse email unique

    @Column(nullable = false)
    private String nom; // Nom de l'utilisateur

    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Reservation> reservations = new HashSet<>(); // Réservations de cet utilisateur

    public Utilisateur() {
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation); // Ajoute la réservation à la liste
        reservation.setUtilisateur(this); // Associe la réservation à cet utilisateur
    }

    public void removeReservation(Reservation reservation) {
        reservations.remove(reservation); // Retire la réservation de la liste
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
