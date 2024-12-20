-- TABLE DE BASE : magie
CREATE TABLE magie(
    idMagie BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(60) NOT NULL,
    cout VARCHAR(10) NOT NULL,
    effet TEXT NOT NULL,
    portee VARCHAR(15),
    duree VARCHAR(35) NOT NULL,
    element VARCHAR(5),
    niveau VARCHAR(35) NOT NULL,
    contre VARCHAR(25),
    profession VARCHAR(9) NOT NULL
);

-- TABLE DE BASE : competence
CREATE TABLE competence(
    idCompetence BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    codeCaracteristique VARCHAR(4) NOT NULL,
    description TEXT NOT NULL,
    descriptionBase10 TEXT NOT NULL,
    descriptionBase13 TEXT NOT NULL,
    descriptionBase16 TEXT NOT NULL,
    descriptionBase20 TEXT NOT NULL
);

-- TABLE DE BASE : caracteristique
CREATE TABLE caracteristique(
    idCaracteristique BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(16) NOT NULL,
    code VARCHAR(6) NOT NULL,
    description TEXT NOT NULL
);

-- TABLE DE BASE : profil_itilisateur
CREATE TABLE profil_utilisateur(
    idProfilUtilisateur BIGINT AUTO_INCREMENT PRIMARY KEY,
    pseudo VARCHAR(64) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    isAdmin BOOLEAN default false,
    UNIQUE(pseudo),
    UNIQUE(email)
);

-- TABLE DE BASE : rituel
CREATE TABLE rituel(
    idRituel BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(60) NOT NULL,
    cout VARCHAR(10) NOT NULL,
    effet TEXT NOT NULL,
    TempsPreparation INT NOT NULL,
    sd VARCHAR(7) NOT NULL,
    duree VARCHAR(10) NOT NULL,
    composant TEXT NOT NULL,
    niveau VARCHAR(20) NOT NULL
);

-- TABLE DE BASE : envoutement
CREATE TABLE envoutement(
    idEnvoutement BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(30) NOT NULL,
    cout VARCHAR(10) NOT NULL,
    effet TEXT NOT NULL,
    prerequis TEXT NOT NULL,
    danger VARCHAR(6) NOT NULL
);

-- TABLE DE BASE : profession
CREATE TABLE profession(
    idProfession BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    codeCaracteristique VARCHAR(4),
    competenceExclusive VARCHAR(50) NOT NULL,
    description TEXT NOT NULL
);

-- TABLE DE BASE : race
CREATE TABLE race(
    idRace BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    categorie VARCHAR(50) NOT NULL
);

-- TABLE DE BASE : profession_personnage
CREATE TABLE profession_personnage(
    idProfessionPersonnage BIGINT AUTO_INCREMENT PRIMARY KEY,
    valeurActuelle INT NOT NULL,
    valeurMax INT NOT NULL,
    idProfession INT NOT NULL,
    FOREIGN KEY(idProfession) REFERENCES profession(idProfession)
);

-- TABLE DE BASE : campagne
CREATE TABLE campagne(
    idCampagne BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    idProfilUtilisateur INT NOT NULL,
    FOREIGN KEY(idProfilUtilisateur) REFERENCES profil_utilisateur(idProfilUtilisateur)
);

-- TABLE DE BASE : competence_specifique
CREATE TABLE competence_specifique(
    idCompetenceSpecifique BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    description TEXT NOT NULL,
    codeCaracteristique VARCHAR(4) NOT NULL,
    specialisation VARCHAR(20) NOT NULL,
    prerequis VARCHAR(20) NOT NULL,
    idProfession INT NOT NULL,
    FOREIGN KEY(idProfession) REFERENCES profession(idProfession)
);

-- TABLE DE BASE : personnage
CREATE TABLE personnage(
    idPersonnage BIGINT AUTO_INCREMENT PRIMARY KEY,
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
    UNIQUE(idProfessionPersonnage),
    FOREIGN KEY(idProfessionPersonnage) REFERENCES profession_personnage(idProfessionPersonnage),
    FOREIGN KEY(idRace) REFERENCES race(idRace),
    FOREIGN KEY(idCampagne) REFERENCES campagne(idCampagne),
    FOREIGN KEY(idProfilUtilisateur) REFERENCES profil_utilisateur(idProfilUtilisateur)
);

-- TABLE DE BASE : profession_specifique_personnage
CREATE TABLE caracteristique_personnage(
    idCaracteristiquePersonnage BIGINT AUTO_INCREMENT PRIMARY KEY,
    idPersonnage BIGINT,
    idCaracteristique BIGINT,
    valeurActuelle INT NOT NULL,
    valeurMax INT NOT NULL,
    FOREIGN KEY(idPersonnage) REFERENCES personnage(idPersonnage),
    FOREIGN KEY(idCaracteristique) REFERENCES caracteristique(idCaracteristique)
);

-- TABLE DE BASE : caracteristique_personnage
CREATE TABLE profession_specifique_personnage(
    idProfessionSpecifiquePersonnage BIGINT AUTO_INCREMENT PRIMARY KEY,
    valeurActuelle INT NOT NULL,
    valeurMax INT NOT NULL,
    idProfession BIGINT NOT NULL,
    idCompetenceSpecifique BIGINT NOT NULL,
    idPersonnage BIGINT NOT NULL,
    FOREIGN KEY(idProfession) REFERENCES profession(idProfession),
    FOREIGN KEY(idCompetenceSpecifique) REFERENCES competence_specifique(idCompetenceSpecifique),
    FOREIGN KEY(idPersonnage) REFERENCES personnage(idPersonnage)
);

-- TABLE DE BASE : competence_personnage
CREATE TABLE competence_personnage(
    idCompetencePersonnage BIGINT AUTO_INCREMENT PRIMARY KEY,
    idPersonnage BIGINT,
    idCompetence BIGINT,
    valeurActuelle INT NOT NULL,
    valeurMax INT NOT NULL,
    FOREIGN KEY(idPersonnage) REFERENCES personnage(idPersonnage),
    FOREIGN KEY(idCompetence) REFERENCES competence(idCompetence)
);

CREATE TABLE magie_personnage(
    idMagiePersonnage BIGINT AUTO_INCREMENT PRIMARY KEY,
    idPersonnage BIGINT,
    idMagie BIGINT,
    FOREIGN KEY(idPersonnage) REFERENCES personnage(idPersonnage),
    FOREIGN KEY(idMagie) REFERENCES magie(idMagie)
);

-- TABLE DE BASE : rituel_personnage
CREATE TABLE rituel_personnage(
    idRituelPersonnage BIGINT AUTO_INCREMENT PRIMARY KEY,
    idPersonnage BIGINT,
    idRituel BIGINT,
    FOREIGN KEY(idPersonnage) REFERENCES personnage(idPersonnage),
    FOREIGN KEY(idRituel) REFERENCES rituel(idRituel)
);

-- TABLE DE BASE : envoutement_personnage
CREATE TABLE envoutement_personnage(
    idEnvoutementPersonnage BIGINT AUTO_INCREMENT PRIMARY KEY,
    idPersonnage BIGINT,
    idEnvoutement BIGINT,
    FOREIGN KEY(idPersonnage) REFERENCES personnage(idPersonnage),
    FOREIGN KEY(idEnvoutement) REFERENCES envoutement(idEnvoutement)
);
