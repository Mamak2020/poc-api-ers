# Emergency Responder Subsystem (ERS)

Emergency Responder Subsystem (ERS) est un système optimisé permettant de gérer les demandes d"intervention urgentes depuis la plateforme des différents intervenants de MedHaed. 
Dans ce repository on trouvera le code java et la documentation technique permettant l'implémentation de ce POC (Plan de tests et Pipeline CI/CD). Dans un autre repository ... on trouvera la documentation complète de l'architecture du projet.

## Application overview

Il s'agit d'un POC permettant de vérifier que le sous sytème ERS de la future plateforme médicale de MedHead est hautement optimisé et que ce système a des niveaux élevés de tolérance aux pannes. 

## Features

- Rechercher l'hopital le plus proche en fonction de la localisation du patient, de la disponibilité d'un lit dans un service spécialisé par rapport à la pathologie du patient.
- En cas de non disponibilité dans la spécialisation médicale, proposer l'hopital le plus proche.
- Réserver un lit dans l'hopital trouvé.
- Retourner les informations sur l'hopital et les instructions pour le transporteur médical.


## REST API - endpoints:

+ `/emergencies GET` retourne l'historique du journal d'intervention des urgences médicales
+ `/emergencies/{id} GET` retourne le détail d'une intervention en urgence médicale
+ `/emergencies POST` demande d'intervention pour une urgence médicale, retourne les coordonnées de l'hôpital de le plus proche
+ `/hospitals GET` retourne la liste des hôpitaux
+ `/hospitals/{id} GET` retourne le détail d'un hôpital
+ `/pathologies GET` retourne la liste des spécialisations et les hôpitaux
+ `/pathologies/{id} GET` retourne la liste des hôpitaux pour une pathologie à traiter

## Persistence

La persistance de ce projet est configurée à l'aide de Spring Data JPA et utilise la base de données "H2" intégrée à java et chargée en mémoire pour faciliter les tests 
et pour l'intégration continue, mais qui est entièrement compatible avec de nombreux technologies de base de données standard comme Postgres. D'ailleurs on utilise une base de 
données Postgres pour les test manuels et automatiques des fonctionnalités de l'API via l'outil Postman. 

## Build

Ce projet utilise Maven. La configuration du Build peut être trouvé dans le `pom.xml`.

Pour build le project:
- cloner le project depuis ce repository
- build le fichier`jar` avec la commande `mvn clean package`
- run le fichier `jar` avec la commande `java -jar ers-0.0.1-SNAPSHOT.jar`

## Tests

Voir le plan de test dans le répertoire doc.

## CI/CD pipeline on Github

Le pipeline sous Github est configuré pour :

  1. Run les tests
  2. Build le projet 
  3. Verify avec SoundCloud
  4. Deploy le fichier jar

La configuration du pipeline sous Github se trouve 
lien sur le fichier [.workflows/maven.yml](.workflows/maven.yml)

### Setting up
