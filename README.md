# Emergency Responder Subsystem (ERS)

Emergency Responder Subsystem (ERS) est un système optimisé permettant de gérer les demandes d"intervention urgentes depuis la plateforme des différents intervenants du Consortium. 
Dans ce repository on trouvera le code java et la documentation technique permettant l'implémentation de ce POC (Plan de tests et Pipeline CI/CD).

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

La persistance de ce projet est configurée à l'aide de Spring Data JPA et utilise la base de données `H2` intégrée à java et chargée en mémoire pour faciliter les tests 
et pour l'intégration continue. H2 est entièrement compatible avec de nombreuses technologies de base de données standard comme Postgres. D'ailleurs on utilise une base de 
données `Postgres` pour les tests des EndPoints de l'API via l'outil `Postman`. 

## Build

Ce projet utilise Maven. La configuration du Build peut être trouvé dans le `pom.xml`.

Pour tester le projet:
- récupérer le fichier [dump-erspoc](postgresql/dump-erspoc) pour l'importer dans l'outil d'adminstration de PostgreSQL pour créer la base de données `erspoc`
- cloner le project depuis ce repository
- build le fichier`jar` avec la commande `mvn clean package` 
- run le fichier `jar` avec la commande `java -jar ers-0.0.1-SNAPSHOT.jar`

## Tests

Voir la documentation du plan de test: [P8_05_Plan_Test.pdf](doc/P8_05_Plan_Test.pdf)

## CI/CD pipeline on Github

Le pipeline sous Github est configuré pour :

  1. Compiler le projet
  2. Exécuter les tests
  3. Vérifier la qualité avec SoundCloud
  4. Construire et déployer le fichier jar

La configuration du pipeline sous Github se trouve 
dans le fichier [maven.yml](.github/workflows/maven.yml)

Voir la documentation du pipeline CI/CD : [P8_06_Pipeline_CICD.pdf](/doc/P8_06_Pipeline_CICD.pdf)

## POC ERS Architecture Repository (doc)
POC Real Time Emergency Responder Subsystem Architecture Repository : https://github.com/Mamak2020/poc-ers-doc


