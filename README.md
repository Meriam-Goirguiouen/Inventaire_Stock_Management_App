# Gestion des stocks et inventaire

Ce projet est une application web de gestion des stocks permettant :
- un suivi en temps réel des produits,
- des opérations CRUD (ajout, suppression, modification),
- et des notifications automatiques lorsque les seuils critiques de stock sont atteints.

---

## Liste des technologies utilisées

- Spring Boot
- Spring MVC
- Thymeleaf 
- JPA + Hibernate 
- Spring JMS 
- MySQL (base de données)

---

## Objectifs fonctionnels

- Gestion des produits (nom, quantité, seuil critique)
- Interface utilisateur simple (ajout, modification, suppression, visualisation)
- Détection des seuils critiques de stock
- Notification automatique via un système de messagerie (JMS)

---

## Répartition de l’équipe

| Membre                | Rôle principal              | Responsabilités                                                                   |
|-----------------------|-----------------------------|-----------------------------------------------------------------------------------|
| GOIRGUIOUEN Meriam    | Backend (JPA + entités)     | Création des entités Produit/Stock, Repository, services métier                   |
| MAZIH Bouchra         | Backend (JMS + alerte)      | Intégration de JMS, système de notification automatique, logique de seuil critique|
| MOUMMADE Karima       | Frontend (Thymeleaf)        | Templates HTML, formulaires CRUD, affichage des alertes                           |
| ZINE Salma            | Frontend (Controller MVC)   | Contrôleurs Spring MVC, liaison backend <-> frontend, gestion des routes          |

---

## Structure actuelle du projet
src/
└── main/
├── java/com/stockapp/
│ ├── controller/ → Contrôleurs MVC (Salma)
│ ├── model/ → Entités JPA (Meriam)
│ ├── repository/ → Interfaces JPA (Meriam)
│ ├── service/ → Logique métier (Meriam + Bouchra)
│ ├── jms/ → Notification/consommation des messages (Bouchra)
│ └── StockApplication.java
└── resources/
├── templates/ → Pages Thymeleaf HTML (Karima)
└── application.properties

