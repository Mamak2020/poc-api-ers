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

+ [/hospitals GET](#list-hospitals)
+ [/hospitals/{id} GET](#find-one-hospital)
+ [/pathologies GET](#list-pathologies)
+ [/pathologies/{id} GET](#find-one-pathologie)
+ [/emergencies GET](#list-emergencies-requests)
+ [/emergencies/{id} GET](#find-one-emergency-request)
+ [/emergencies POST](#create-emergency-request)

## Build

This project is set up using Maven. Build configuration can be found at `pom.xml`.

To build project:
- clone project from this repository
- build `jar` file using command `mvn clean package`
- run `jar` file using command `java -jar ers-0.0.1-SNAPSHOT.jar`

## Tests

Tests are separated into two classpaths: `src/test` for unit tests, and `src/itest` for integration tests. 

To run unit tests:

  `mvn clean test`
  
To run integration tests

   `mvn clean test -Pintegration-testing`
   
To run performance tests 
    `mvn ....`

## CI/CD pipeline on GitLab

Le pipeline sous GitLab est configuré pour :

  1. Build le projet
  2. Run les tests
  3. Build Docker image
  4. Push image sur `hub.docker.com`

La configuration du pipeline sous GitLab se trouve 
lien sur le fichier .yml

### Setting up