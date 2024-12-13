-- TABLE DE BASE : magie
CREATE TABLE magie(
    idMagie BIGINT,
    nom VARCHAR(60) NOT NULL,
    cout VARCHAR(10) NOT NULL,
    effet TEXT NOT NULL,
    portee VARCHAR(10) NOT NULL,
    duree VARCHAR(10) NOT NULL,
    elementaire VARCHAR(5) NOT NULL,
    niveau VARCHAR(20) NOT NULL,
    contre VARCHAR(20),
    profession VARCHAR(9) NOT NULL,
    PRIMARY KEY(idMagie)
);

-- TABLE DE BASE : competence
CREATE TABLE competence(
    idCompetence BIGINT,
    nom VARCHAR(50) NOT NULL,
    codeCaracteristique VARCHAR(4) NOT NULL,
    description TEXT NOT NULL,
    descriptionBase10 TEXT NOT NULL,
    descriptionBase13 TEXT NOT NULL,
    descriptionBase16 TEXT NOT NULL,
    descriptionBase20 TEXT NOT NULL,
    PRIMARY KEY(idCompetence)
);

-- TABLE DE BASE : caracteristique
CREATE TABLE caracteristique(
    idCaracteristique BIGINT,
    nom VARCHAR(16) NOT NULL,
    code VARCHAR(6) NOT NULL,
    description TEXT NOT NULL,
    PRIMARY KEY(idCaracteristique)
);

-- TABLE DE BASE : profil_itilisateur
CREATE TABLE profil_utilisateur(
    idProfilUtilisateur BIGINT,
    pseudo VARCHAR(64) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    isAdmin BOOLEAN default false,
    PRIMARY KEY(idProfilUtilisateur),
    UNIQUE(pseudo),
    UNIQUE(email)
);

-- TABLE DE BASE : rituel
CREATE TABLE rituel(
    idRituel BIGINT,
    nom VARCHAR(60) NOT NULL,
    cout VARCHAR(10) NOT NULL,
    effet TEXT NOT NULL,
    TempsPreparation INT NOT NULL,
    sd VARCHAR(7) NOT NULL,
    duree VARCHAR(10) NOT NULL,
    composant TEXT NOT NULL,
    niveau VARCHAR(20) NOT NULL,
    PRIMARY KEY(idRituel)
);

-- TABLE DE BASE : envoutement
CREATE TABLE envoutement(
    idEnvoutement BIGINT,
    nom VARCHAR(60) NOT NULL,
    cout VARCHAR(10) NOT NULL,
    effet TEXT NOT NULL,
    prerequis TEXT NOT NULL,
    danger VARCHAR(6) NOT NULL,
    PRIMARY KEY(idEnvoutement)
);

-- TABLE DE BASE : profession
CREATE TABLE profession(
    idProfession BIGINT,
    nom VARCHAR(50) NOT NULL,
    competenceExclusive VARCHAR(50) NOT NULL,
    PRIMARY KEY(idProfession)
);

-- TABLE DE BASE : race
CREATE TABLE race(
    idRace BIGINT,
    nom VARCHAR(50) NOT NULL,
    categorie VARCHAR(50) NOT NULL,
    PRIMARY KEY(idRace)
);

-- TABLE DE BASE : profession_personnage
CREATE TABLE profession_personnage(
    idProfessionPersonnage BIGINT,
    valeurActuelle INT NOT NULL,
    valeurMax INT NOT NULL,
    idProfession INT NOT NULL,
    PRIMARY KEY(idProfessionPersonnage),
    FOREIGN KEY(idProfession) REFERENCES profession(idProfession)
);

-- TABLE DE BASE : campagne
CREATE TABLE campagne(
    idCampagne BIGINT,
    nom VARCHAR(50) NOT NULL,
    idProfilUtilisateur INT NOT NULL,
    PRIMARY KEY(idCampagne),
    FOREIGN KEY(idProfilUtilisateur) REFERENCES profil_utilisateur(idProfilUtilisateur)
);

-- TABLE DE BASE : competence_specifique
CREATE TABLE competence_specifique(
    idCompetenceSpecifique BIGINT,
    nom VARCHAR(50) NOT NULL,
    description TEXT NOT NULL,
    codeCaracteristique VARCHAR(4) NOT NULL,
    specialisation VARCHAR(20) NOT NULL,
    prerequis VARCHAR(20) NOT NULL,
    idProfession INT NOT NULL,
    PRIMARY KEY(idCompetenceSpecifique),
    FOREIGN KEY(idProfession) REFERENCES profession(idProfession)
);

-- TABLE DE BASE : personnage
CREATE TABLE personnage(
    idPersonnage BIGINT,
    nomPersonnage VARCHAR(50),
    nomJoueur VARCHAR(50),
    nomImage VARCHAR(100),
    urlImage TEXT,
    genre CHAR(1),
    terreNatale VARCHAR(20),
    xp INT,
    age INT,
    bestiaire BOOLEAN default false,
    idProfessionPersonnage INT NOT NULL,
    idRace BIGINT NOT NULL,
    idCampagne BIGINT,
    idProfilUtilisateur BIGINT,
    PRIMARY KEY(idPersonnage),
    UNIQUE(idProfessionPersonnage),
    FOREIGN KEY(idProfessionPersonnage) REFERENCES profession_personnage(idProfessionPersonnage),
    FOREIGN KEY(idRace) REFERENCES race(idRace),
    FOREIGN KEY(idCampagne) REFERENCES campagne(idCampagne),
    FOREIGN KEY(idProfilUtilisateur) REFERENCES profil_utilisateur(idProfilUtilisateur)
);

-- TABLE DE BASE : profession_specifique_personnage
CREATE TABLE caracteristique_personnage(
    idCaracteristiquePersonnage BIGINT,
    idPersonnage BIGINT,
    idCaracteristique BIGINT,
    valeurActuelle INT NOT NULL,
    valeurMax INT NOT NULL,
    PRIMARY KEY(idCaracteristiquePersonnage),
    FOREIGN KEY(idPersonnage) REFERENCES personnage(idPersonnage),
    FOREIGN KEY(idCaracteristique) REFERENCES caracteristique(idCaracteristique)
);

-- TABLE DE BASE : caracteristique_personnage
CREATE TABLE profession_specifique_personnage(
    idProfessionSpecifiquePersonnage BIGINT,
    valeurActuelle INT NOT NULL,
    valeurMax INT NOT NULL,
    idProfession BIGINT NOT NULL,
    idCompetenceSpecifique BIGINT NOT NULL,
    idPersonnage BIGINT NOT NULL,
    PRIMARY KEY(idProfessionSpecifiquePersonnage),
    FOREIGN KEY(idProfession) REFERENCES profession(idProfession),
    FOREIGN KEY(idCompetenceSpecifique) REFERENCES competence_specifique(idCompetenceSpecifique),
    FOREIGN KEY(idPersonnage) REFERENCES personnage(idPersonnage)
);

-- TABLE DE BASE : competence_personnage
CREATE TABLE competence_personnage(
    idCompetencePersonnage BIGINT,
    idPersonnage BIGINT,
    idCompetence BIGINT,
    valeurActuelle INT NOT NULL,
    valeurMax INT NOT NULL,
    PRIMARY KEY(idCompetencePersonnage),
    FOREIGN KEY(idPersonnage) REFERENCES personnage(idPersonnage),
    FOREIGN KEY(idCompetence) REFERENCES competence(idCompetence)
);

CREATE TABLE magie_personnage(
    idMagiePersonnage BIGINT,
    idPersonnage BIGINT,
    idMagie BIGINT,
    PRIMARY KEY(idMagiePersonnage),
    FOREIGN KEY(idPersonnage) REFERENCES personnage(idPersonnage),
    FOREIGN KEY(idMagie) REFERENCES magie(idMagie)
);

-- TABLE DE BASE : rituel_personnage
CREATE TABLE rituel_personnage(
    idRituelPersonnage BIGINT,
    idPersonnage BIGINT,
    idRituel BIGINT,
    PRIMARY KEY(idRituelPersonnage),
    FOREIGN KEY(idPersonnage) REFERENCES personnage(idPersonnage),
    FOREIGN KEY(idRituel) REFERENCES rituel(idRituel)
);

-- TABLE DE BASE : envoutement_personnage
CREATE TABLE envoutement_personnage(
    idEnvoutementPersonnage BIGINT,
    idPersonnage BIGINT,
    idEnvoutement BIGINT,
    PRIMARY KEY(idEnvoutementPersonnage),
    FOREIGN KEY(idPersonnage) REFERENCES personnage(idPersonnage),
    FOREIGN KEY(idEnvoutement) REFERENCES envoutement(idEnvoutement)
);
