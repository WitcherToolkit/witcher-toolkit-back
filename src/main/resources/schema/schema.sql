CREATE TABLE magie(
    id_magie INT AUTO_INCREMENT,
    nom VARCHAR(60) NOT NULL,
    cout VARCHAR(10) NOT NULL,
    effet TEXT NOT NULL,
    portee VARCHAR(15),
    duree VARCHAR(35) NOT NULL,
    nature VARCHAR(5) NOT NULL,
    niveau VARCHAR(35) NOT NULL,
    contre VARCHAR(25),
    type VARCHAR(10) NOT NULL,
    PRIMARY KEY(id_magie)
);

CREATE TABLE rituel(
    id_rituel INT AUTO_INCREMENT,
    nom VARCHAR(60) NOT NULL,
    cout VARCHAR(10) NOT NULL,
    effet TEXT NOT NULL,
    temps_preparation VARCHAR(10) NOT NULL,
    sd VARCHAR(7) NOT NULL,
    duree VARCHAR(15) NOT NULL,
    composant TEXT NOT NULL,
    niveau VARCHAR(20) NOT NULL,
    PRIMARY KEY(id_rituel)
);

CREATE TABLE envoutement(
    id_envoutement INT AUTO_INCREMENT,
    nom VARCHAR(60) NOT NULL,
    cout VARCHAR(10) NOT NULL,
    effet TEXT NOT NULL,
    prerequis TEXT NOT NULL,
    danger VARCHAR(6) NOT NULL,
    PRIMARY KEY(id_envoutement)
);

CREATE TABLE caracteristique(
    id_caracteristique INT AUTO_INCREMENT,
    nom VARCHAR(16) NOT NULL,
    code VARCHAR(6) NOT NULL,
    description TEXT NOT NULL,
    PRIMARY KEY(id_caracteristique)
);

CREATE TABLE competence(
    id_competence INT AUTO_INCREMENT,
    nom VARCHAR(50) NOT NULL,
    description TEXT NOT NULL,
    is_exclusive BOOLEAN NOT NULL,
    specialisation VARCHAR(20),
    prerequis VARCHAR(20),
    id_caracteristique INT NOT NULL,
    PRIMARY KEY(id_competence),
    FOREIGN KEY(id_caracteristique) REFERENCES caracteristique(id_caracteristique)
);

CREATE TABLE race(
     id_race INT AUTO_INCREMENT,
     nom VARCHAR(50) NOT NULL,
     PRIMARY KEY(id_race)
);

CREATE TABLE particularite(
    id_Particularite INT AUTO_INCREMENT,
    nom VARCHAR(50) NOT NULL,
    description TEXT NOT NULL,
    id_race INT NOT NULL,
    PRIMARY KEY(id_Particularite),
    FOREIGN KEY(id_race) REFERENCES race(id_race)
);

CREATE TABLE reputation_wiki(
    id_reputation_wiki INT AUTO_INCREMENT,
    territoire VARCHAR(20) NOT NULL,
    valeur VARCHAR(20) NOT NULL,
    id_race INT NOT NULL,
    PRIMARY KEY(id_reputation_wiki),
    FOREIGN KEY(id_race) REFERENCES race(id_race)
    );

CREATE TABLE profil_utilisateur(
    id_user INT AUTO_INCREMENT,
    pseudo VARCHAR(64) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    admin BOOLEAN NOT NULL default false,
    PRIMARY KEY(id_user),
    UNIQUE(pseudo),
    UNIQUE(email)
);

CREATE TABLE profession(
    id_profession INT AUTO_INCREMENT,
    nom VARCHAR(50) NOT NULL,
    description TEXT NOT NULL,
    vigueur INT,
    max_sort INT NOT NULL DEFAULT 0,
    max_rituel INT NOT NULL DEFAULT 0,
    max_envoutement INT NOT NULL DEFAULT 0,
    max_invocation INT NOT NULL DEFAULT 0,

    PRIMARY KEY(id_profession)
);

CREATE TABLE inventaire_wiki(
    id_inventaire_wiki INT AUTO_INCREMENT,
    quantite INT NOT NULL DEFAULT 1,
    nom VARCHAR(50) NOT NULL,
    type VARCHAR(10),
    effet TEXT,
    is_special BOOLEAN NOT NULL DEFAULT FALSE,
    id_profession INT NOT NULL,
    PRIMARY KEY(id_inventaire_wiki),
    FOREIGN KEY(id_profession) REFERENCES profession(id_profession)
);

CREATE TABLE competence_profession(
    id_comptetence_profession INT AUTO_INCREMENT,
    id_profession INT,
    id_competence INT NOT NULL,
    PRIMARY KEY(id_comptetence_profession, id_profession),
    FOREIGN KEY(id_profession) REFERENCES profession(id_profession),
    FOREIGN KEY(id_competence) REFERENCES competence(id_competence)
);

CREATE TABLE campagne(
    id_campagne INT AUTO_INCREMENT,
    nom VARCHAR(50) NOT NULL,
    id_user INT NOT NULL,
    PRIMARY KEY(id_campagne),
    FOREIGN KEY(id_user) REFERENCES profil_utilisateur(id_user)
);

CREATE TABLE personnage(
    id_personnage INT AUTO_INCREMENT,
    nom_personnage VARCHAR(50),
    nom_joueur VARCHAR(50),
    nom_image VARCHAR(100),
    url_umage TEXT,
    genre CHAR(1),
    terre_natale VARCHAR(20),
    xp INT,
    age INT,
    bestiaire BOOLEAN default false,
    historique TEXT,
    poings VARCHAR(10),
    pieds VARCHAR(10),
    id_profession INT NOT NULL,
    id_race INT NOT NULL,
    id_campagne INT,
    id_user INT,
    PRIMARY KEY(id_personnage),
    FOREIGN KEY(id_profession) REFERENCES profession(id_profession),
    FOREIGN KEY(id_race) REFERENCES race(id_race),
    FOREIGN KEY(id_campagne) REFERENCES campagne(id_campagne),
    FOREIGN KEY(id_user) REFERENCES profil_utilisateur(id_user)
);

CREATE TABLE inventaire(
    id_inventaire INT AUTO_INCREMENT,
    nom VARCHAR(60) NOT NULL,
    type VARCHAR(10),
    effet TEXT,
    quantite INT,
    id_personnage INT NOT NULL,
    PRIMARY KEY(id_inventaire),
    FOREIGN KEY(id_personnage) REFERENCES personnage(id_personnage)
);

CREATE TABLE caracteristique_personnage(
    id_caracteristique_personnage INT AUTO_INCREMENT,
    valeur_actuelle INT NOT NULL,
    valeur_max INT NOT NULL,
    id_personnage INT NOT NULL,
    id_caracteristique INT NOT NULL,
    PRIMARY KEY(id_caracteristique_personnage),
    FOREIGN KEY(id_personnage) REFERENCES personnage(id_personnage),
    FOREIGN KEY(id_caracteristique) REFERENCES caracteristique(id_caracteristique)
);

CREATE TABLE reputation_personnalisee(
    id_reputation INT AUTO_INCREMENT,
    territoire VARCHAR(20) NOT NULL,
    valeur VARCHAR(20) NOT NULL,
    id_personnage INT NOT NULL,
    PRIMARY KEY(id_reputation),
    FOREIGN KEY(id_personnage) REFERENCES personnage(id_personnage)
);

CREATE TABLE competence_personnage(
    id_caracteristique_personnage INT AUTO_INCREMENT,
    valeur_actuelle INT NOT NULL,
    valeur_max INT NOT NULL,
    id_personnage INT NOT NULL,
    id_competence INT NOT NULL,
    PRIMARY KEY(id_caracteristique_personnage),
    UNIQUE (id_personnage, id_competence),
    FOREIGN KEY(id_personnage) REFERENCES personnage(id_personnage),
    FOREIGN KEY(id_competence) REFERENCES competence(id_competence)
);

CREATE TABLE magie_personnage(
    id_magie_personnage INT AUTO_INCREMENT,
    id_personnage INT,
    id_magie INT,
    PRIMARY KEY(id_magie_personnage),
    UNIQUE (id_personnage, id_magie),
    FOREIGN KEY(id_personnage) REFERENCES personnage(id_personnage),
    FOREIGN KEY(id_magie) REFERENCES magie(id_magie)
);

CREATE TABLE rituel_ersonnage(
    id_rituel_personnage INT AUTO_INCREMENT,
    id_personnage INT,
    id_rituel INT,
    PRIMARY KEY(id_rituel_personnage),
    UNIQUE (id_personnage, id_rituel),
    FOREIGN KEY(id_personnage) REFERENCES personnage(id_personnage),
    FOREIGN KEY(id_rituel) REFERENCES rituel(id_rituel)
);

CREATE TABLE envoutement_personnage(
    id_envoutement_personnage INT AUTO_INCREMENT,
    id_personnage INT,
    id_envoutement INT,
    PRIMARY KEY(id_envoutement_personnage),
    UNIQUE (id_personnage, id_envoutement),
    FOREIGN KEY(id_personnage) REFERENCES personnage(id_personnage),
    FOREIGN KEY(id_envoutement) REFERENCES envoutement(id_envoutement)
);
