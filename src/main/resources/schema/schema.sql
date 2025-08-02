CREATE TABLE magie(
    idMagie INT,
    nom VARCHAR(60) NOT NULL,
    cout VARCHAR(10) NOT NULL,
    effet TEXT NOT NULL,
    portee VARCHAR(15),
    duree VARCHAR(35) NOT NULL,
    nature VARCHAR(5) NOT NULL,
    niveau VARCHAR(35) NOT NULL,
    contre VARCHAR(25),
    type VARCHAR(10) NOT NULL,
    PRIMARY KEY(idMagie)
);

CREATE TABLE rituel(
    idRituel INT,
    nom VARCHAR(60) NOT NULL,
    cout VARCHAR(10) NOT NULL,
    effet TEXT NOT NULL,
    TempsPreparation VARCHAR(10) NOT NULL,
    sd VARCHAR(7) NOT NULL,
    duree VARCHAR(15) NOT NULL,
    composant TEXT NOT NULL,
    niveau VARCHAR(20) NOT NULL,
    PRIMARY KEY(idRituel)
);

CREATE TABLE envoutement(
    idEnvoutement INT,
    nom VARCHAR(60) NOT NULL,
    cout VARCHAR(10) NOT NULL,
    effet TEXT NOT NULL,
    prerequis TEXT NOT NULL,
    danger VARCHAR(6) NOT NULL,
    PRIMARY KEY(idEnvoutement)
);

CREATE TABLE caracteristique(
    idCaracteristique INT,
    nom VARCHAR(16) NOT NULL,
    code VARCHAR(6) NOT NULL,
    description TEXT NOT NULL,
    PRIMARY KEY(idCaracteristique)
);

CREATE TABLE profilUtilisateur(
    idUser INT,
    pseudo VARCHAR(64) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    isAdmin BOOLEAN NOT NULL default false,
    PRIMARY KEY(idUser),
    UNIQUE(pseudo),
    UNIQUE(email)
);

CREATE TABLE competence(
    idCompetence INT,
    nom VARCHAR(50) NOT NULL,
    tags VARCHAR(50),
    description TEXT NOT NULL,
    specialisation VARCHAR(20),
    prerequis VARCHAR(20),
    isExclusive BOOLEAN NOT NULL,
    idCaracteristique INT NOT NULL,
    PRIMARY KEY(idCompetence),
    FOREIGN KEY(idCaracteristique) REFERENCES caracteristique(idCaracteristique)
);

CREATE TABLE profession(
    idProfession INT,
    nom VARCHAR(50) NOT NULL,
    description TEXT NOT NULL,
    vigueur INT,
    maxSort INT NOT NULL DEFAULT 0,
    maxRituel INT NOT NULL DEFAULT 0,
    maxEnvoutement INT NOT NULL DEFAULT 0,
    maxInvocation INT NOT NULL DEFAULT 0,

    PRIMARY KEY(idProfession)
);

CREATE TABLE inventaireWiki(
    idInventaireWiki INT,
    quantite INT NOT NULL DEFAULT 1,
    nom VARCHAR(50) NOT NULL,
    type VARCHAR(10),
    effet TEXT,
    isSpecial BOOLEAN NOT NULL DEFAULT FALSE,
    idProfession INT NOT NULL,
    PRIMARY KEY(idInventaireWiki),
    FOREIGN KEY(idProfession) REFERENCES profession(idProfession)
);

CREATE TABLE race(
    idRace INT,
    nom VARCHAR(50) NOT NULL,
    PRIMARY KEY(idRace)
);

CREATE TABLE reputationWiki(
    idReputationWiki INT,
    territoire VARCHAR(20) NOT NULL,
    valeur VARCHAR(20) NOT NULL,
    idRace INT NOT NULL,
    PRIMARY KEY(idReputationWiki),
    FOREIGN KEY(idRace) REFERENCES race(idRace)
);

CREATE TABLE particularite(
    idParticularite INT,
    nom VARCHAR(50) NOT NULL,
    description TEXT NOT NULL,
    idRace INT NOT NULL,
    PRIMARY KEY(idParticularite),
    FOREIGN KEY(idRace) REFERENCES race(idRace)
);

CREATE TABLE competenceProfession(
    idComptetenceProfession INT,
    idProfession INT,
    idCompetence INT NOT NULL,
    PRIMARY KEY(idComptetenceProfession, idProfession),
    FOREIGN KEY(idProfession) REFERENCES profession(idProfession),
    FOREIGN KEY(idCompetence) REFERENCES competence(idCompetence)
);

CREATE TABLE campagne(
    idCampagne INT,
    nom VARCHAR(50) NOT NULL,
    uId TEXT NOT NULL,
    password TEXT,
    idUser INT NOT NULL,
    PRIMARY KEY(idCampagne),
    UNIQUE(uId),
    FOREIGN KEY(idUser) REFERENCES profilUtilisateur(idUser)
);

CREATE TABLE personnage(
    idPersonnage INT,
    nomPersonnage VARCHAR(50),
    nomJoueur VARCHAR(50),
    nomImage VARCHAR(100),
    urlImage TEXT,
    genre CHAR(1),
    terreNatale VARCHAR(20),
    xp INT,
    age INT,
    bestiaire BOOLEAN default false,
    historique TEXT,
    poings VARCHAR(10),
    pieds VARCHAR(10),
    idProfession INT NOT NULL,
    idRace INT NOT NULL,
    idCampagne INT,
    idUser INT,
    PRIMARY KEY(idPersonnage),
    FOREIGN KEY(idProfession) REFERENCES profession(idProfession),
    FOREIGN KEY(idRace) REFERENCES race(idRace),
    FOREIGN KEY(idCampagne) REFERENCES campagne(idCampagne),
    FOREIGN KEY(idUser) REFERENCES profilUtilisateur(idUser)
);

CREATE TABLE inventaire(
    idEnvoutement INT,
    nom VARCHAR(60) NOT NULL,
    type VARCHAR(10),
    effet TEXT,
    quantite INT,
    idPersonnage INT NOT NULL,
    PRIMARY KEY(idEnvoutement),
    FOREIGN KEY(idPersonnage) REFERENCES personnage(idPersonnage)
);

CREATE TABLE caracteristiquePersonnage(
    idCaracteristiquePersonnage INT,
    valeurActuelle INT NOT NULL,
    valeurMax INT NOT NULL,
    idPersonnage INT NOT NULL,
    idCaracteristique INT NOT NULL,
    PRIMARY KEY(idCaracteristiquePersonnage),
    FOREIGN KEY(idPersonnage) REFERENCES personnage(idPersonnage),
    FOREIGN KEY(idCaracteristique) REFERENCES caracteristique(idCaracteristique)
);

CREATE TABLE reputationPersonnalisee(
    idReputation INT,
    territoire VARCHAR(20) NOT NULL,
    valeur VARCHAR(20) NOT NULL,
    idPersonnage INT NOT NULL,
    PRIMARY KEY(idReputation),
    FOREIGN KEY(idPersonnage) REFERENCES personnage(idPersonnage)
);

CREATE TABLE competencePersonnage(
    idCaracteristiquePersonnage INT,
    valeurActuelle INT NOT NULL,
    valeurMax INT NOT NULL,
    idPersonnage INT NOT NULL,
    idCompetence INT NOT NULL,
    PRIMARY KEY(idCaracteristiquePersonnage),
    FOREIGN KEY(idPersonnage) REFERENCES personnage(idPersonnage),
    FOREIGN KEY(idCompetence) REFERENCES competence(idCompetence)
);

CREATE TABLE magiePersonnage(
    idPersonnage INT,
    idMagie INT,
    PRIMARY KEY(idPersonnage, idMagie),
    FOREIGN KEY(idPersonnage) REFERENCES personnage(idPersonnage),
    FOREIGN KEY(idMagie) REFERENCES magie(idMagie)
);

CREATE TABLE rituelPersonnage(
    idPersonnage INT,
    idRituel INT,
    PRIMARY KEY(idPersonnage, idRituel),
    FOREIGN KEY(idPersonnage) REFERENCES personnage(idPersonnage),
    FOREIGN KEY(idRituel) REFERENCES rituel(idRituel)
);

CREATE TABLE envoutementPersonnage(
    idPersonnage INT,
    idEnvoutement INT,
    PRIMARY KEY(idPersonnage, idEnvoutement),
    FOREIGN KEY(idPersonnage) REFERENCES personnage(idPersonnage),
    FOREIGN KEY(idEnvoutement) REFERENCES envoutement(idEnvoutement)
);
