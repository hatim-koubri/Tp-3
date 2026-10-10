## Captures des résultats

### 1. Création des tables
Hibernate crée les tables `Equipement`, `Reservation`, `Salle`, `Utilisateur` et `salle_equipement` dans la base H2.

<img width="1572" height="936" alt="1" src="https://github.com/user-attachments/assets/29b6b5b3-eed5-4bb7-9233-64fb1d8533ee" />

### 2. Création des contraintes et des relations
Hibernate ajoute les contraintes d’unicité et les clés étrangères entre les entités.

<img width="1735" height="1032" alt="2" src="https://github.com/user-attachments/assets/44f876f5-7698-41f2-8b4c-2457ce0e3598" />


### 3. Insertion des entités
Le programme enregistre la salle, l’équipement, l’utilisateur et la réservation, puis ajoute l’association dans `salle_equipement`.


<img width="1400" height="930" alt="3" src="https://github.com/user-attachments/assets/5ba52b97-4960-4f6d-970b-fd8f39338208" />


### 4. Suppression de la réservation
La réservation est supprimée grâce à `orphanRemoval`. Le programme se termine avec le code `0`.

<img width="1612" height="1042" alt="4" src="https://github.com/user-attachments/assets/614e52a1-8045-4201-8e18-a23b2e25699b" />
