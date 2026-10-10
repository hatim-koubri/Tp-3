package com.demo;

import com.demo.entites.Equipement;
import com.demo.entites.Reservation;
import com.demo.entites.Salle;
import com.demo.entites.Utilisateur;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class App {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestion-reservations-pu");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin(); // Commence la transaction

            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setEmail("alice@example.com");
            utilisateur.setNom("Alice Dupont");

            Salle salle = new Salle();
            salle.setNom("Salle A");

            Equipement projecteur = new Equipement();
            projecteur.setNom("Projecteur");
            salle.addEquipement(projecteur); // Associe l'équipement à la salle

            Reservation reservation = new Reservation();
            reservation.setDateDebut(LocalDateTime.now().plusDays(1));
            reservation.setDateFin(LocalDateTime.now().plusDays(1).plusHours(2));
            utilisateur.addReservation(reservation); // Associe la réservation à l'utilisateur
            salle.addReservation(reservation); // Associe la réservation à la salle

            em.persist(salle); // Enregistre la salle et son équipement
            em.persist(utilisateur); // Enregistre l'utilisateur et sa réservation

            em.getTransaction().commit(); // Valide les changements

            System.out.println("Utilisateur enregistré : " + utilisateur.getNom());
            System.out.println("Salle enregistrée : " + salle.getNom());
            System.out.println("Réservation enregistrée : " + reservation.getId());

            em.getTransaction().begin(); // Commence une nouvelle transaction
            Utilisateur utilisateurCharge = em.find(Utilisateur.class, utilisateur.getId());
            Reservation reservationChargee = utilisateurCharge.getReservations().iterator().next();
            utilisateurCharge.removeReservation(reservationChargee); // Supprime la réservation orpheline
            em.getTransaction().commit(); // Valide la suppression

            System.out.println("Réservation supprimée avec orphanRemoval.");

        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Annule la transaction en cas d'erreur
            }
            throw e;
        } finally {
            em.close(); // Ferme l'EntityManager
            emf.close(); // Ferme la factory
        }
    }
}
