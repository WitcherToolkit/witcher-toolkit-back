campagne = (idCampagne INT, nom VARCHAR(50), #idUser);

caracteristique = (idCaractéristique INT, nom VARCHAR(16), code VARCHAR(4), description TEXT);

caracteristique_personnage = (#idPersonnage, #idCaractéristique, id INT, valeurActuelle INT, valeurMax INT);

competence = (idCompetence INT, nom VARCHAR(50), codeCaracteristique VARCHAR(4), descriptionBase10 TEXT, descriptionBase13 TEXT, descriptionBase16 TEXT, descriptionBase20 TEXT);

competence_personnage = (#idPersonnage, #idCompetence, id INT, valeurActuelle INT, valeurMax INT);

competence_specifique = (idCompetenceSpecifique INT, nom VARCHAR(50), description TEXT, codeCaracteristique VARCHAR(4), specialisation VARCHAR(20), prerequis VARCHAR(20), #idProfession);

envoutement = (idEnvoutement INT, nom VARCHAR(60), cout VARCHAR(10), effet TEXT, prerequis TEXT, danger VARCHAR(6));

envoutement_personnage = (#idPersonnage, #idEnvoutement);

user = (idUser INT, pseudo VARCHAR(64), email VARCHAR(255), password VARCHAR(255), isAdmin LOGICAL);

magie = (idMagie INT, nom VARCHAR(60), cout VARCHAR(10), effet TEXT, portee VARCHAR(10), duree VARCHAR(10), elementaire VARCHAR(5), niveau VARCHAR(20), contre VARCHAR(20), profession VARCHAR(9));

magie_personnage = (#idPersonnage, #idMagie);

personnage = (idPersonnage INT, nomPersonnage VARCHAR(50), nomJoueur VARCHAR(50), nomImage VARCHAR(100), urlImage TEXT, genre CHAR(1), terreNatale VARCHAR(20), xp INT, age INT, bestiaire LOGICAL, #idCampagne, #idRace, #idCampagne_1*, #idUser*);

profession = (idProfession INT, nom VARCHAR(50), competenceExclusive VARCHAR(50));

profession_personnage = (idCampagne INT, valeurActuelle INT, valeurMax INT, #idProfession);

profession_specifique_personnage = (idCampagne INT, valeurActuelle INT, valeurMax INT, #idProfession, #idCompetenceSpecifique, #idPersonnage);

race = (idRace INT, nom VARCHAR(50), categorie VARCHAR(50));

rituel = (idRituel INT, nom VARCHAR(60), cout VARCHAR(10), effet TEXT, TempsPreparation INT, sd VARCHAR(7), duree VARCHAR(10), composant TEXT, niveau VARCHAR(20));

rituel_personnage = (#idPersonnage, #idRituel);

Remarques :
Pour récupérer un personnage et ses caractéristiques associées, nous pouvons utiliser une requête JPQL comme celle-ci :
````java
TypedQuery<Personnage> query = entityManager.createQuery("SELECT p FROM Personnage p JOIN FETCH p.caracteristiquesPersonnages cp JOIN FETCH cp.caracteristique", Personnage.class);
List<Personnage> personnages = query.getResultList();
````
JOIN FETCH: Ces clauses permettent de charger les données liées (les CaracteristiquePersonnage et les Caracteristique) en une seule requête, évitant ainsi les N+1 problèmes.

Exemple structure hexagonale
```
src/main/java/fr/meya/witcher/
├── appication/
│   ├── mapper/
│   │   └── MagieMapper.java
│   └── service/
│       └── MagieService.java
├── common/
│   ├── utils/
│   │   ├── ValidationUtils.java
│   │   ├── ValidationRule.java
│   │   └── ObjectUtils.java
├── domain/
│   ├── model/
│   │   └── persistent
│   │   	└── Magie.java
│   └── port/
│       └── in
│       	└── IMagieService.java
├── exeption/
│       └── WitcherToolkitExeption.java
├── infrastructure/
│   ├── adapter/
│   │   ├── in
│   │   │	└── MagieController.java
│   │   └── out
│   │    	└── IMagieRepository.java
│   └── congif
│       ├── ApplicationConfig.java
│       └── MessageConfig.java
└── message/
│       └── response
│        	└── MagieVolatile.java
src/main/resources/
└── messages.properties
```