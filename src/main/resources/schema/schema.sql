
CREATE TABLE IF NOT EXISTS magie(
    id_magie INT AUTO_INCREMENT,
    nom VARCHAR(60) NOT NULL,
    cout VARCHAR(10) NOT NULL,
    effet TEXT NOT NULL,
    portee VARCHAR(20),
    duree VARCHAR(35) NOT NULL,
    nature VARCHAR(5) NOT NULL,
    niveau VARCHAR(35) NOT NULL,
    contre VARCHAR(25),
    type VARCHAR(10) NOT NULL,
    PRIMARY KEY(id_magie)
);

CREATE TABLE IF NOT EXISTS rituel(
    id_rituel INT AUTO_INCREMENT,
    nom VARCHAR(60) NOT NULL,
    cout VARCHAR(10) NOT NULL,
    effet TEXT NOT NULL,
    temps_preparation VARCHAR(10) NOT NULL,
    sd VARCHAR(10) NOT NULL,
    duree VARCHAR(15) NOT NULL,
    composant TEXT NOT NULL,
    niveau VARCHAR(20) NOT NULL,
    PRIMARY KEY(id_rituel)
);

CREATE TABLE IF NOT EXISTS envoutement(
    id_envoutement INT AUTO_INCREMENT,
    nom VARCHAR(60) NOT NULL,
    cout VARCHAR(10) NOT NULL,
    effet TEXT NOT NULL,
    prerequis TEXT NOT NULL,
    danger VARCHAR(6) NOT NULL,
    PRIMARY KEY(id_envoutement)
);

CREATE TABLE IF NOT EXISTS caracteristique(
    id_caracteristique INT AUTO_INCREMENT,
    nom VARCHAR(16) NOT NULL,
    code VARCHAR(6) NOT NULL,
    description TEXT NOT NULL,
    type VARCHAR(11) NOT NULL,
    PRIMARY KEY(id_caracteristique)
);

CREATE TABLE IF NOT EXISTS competence(
    id_competence INT AUTO_INCREMENT,
    nom VARCHAR(50) NOT NULL,
    description TEXT NOT NULL,
    exclusive BOOLEAN NOT NULL,
    specialisation VARCHAR(20),
    prerequis VARCHAR(20),
    step int,
    type VARCHAR(10),
    id_caracteristique INT NOT NULL,
    PRIMARY KEY(id_competence),
    CONSTRAINT fk_competence_caracteristique FOREIGN KEY(id_caracteristique) REFERENCES caracteristique(id_caracteristique)
);

CREATE TABLE IF NOT EXISTS race(
     id_race INT AUTO_INCREMENT,
     nom VARCHAR(50) NOT NULL,
     PRIMARY KEY(id_race)
);

CREATE TABLE IF NOT EXISTS particularite(
    id_Particularite INT AUTO_INCREMENT,
    nom VARCHAR(50) NOT NULL,
    description TEXT NOT NULL,
    id_race INT NOT NULL,
    PRIMARY KEY(id_Particularite),
    CONSTRAINT fk_particularite_race FOREIGN KEY(id_race) REFERENCES race(id_race)
);

CREATE TABLE IF NOT EXISTS reputation_wiki(
    id_reputation_wiki INT AUTO_INCREMENT,
    territoire VARCHAR(20) NOT NULL,
    valeur VARCHAR(20) NOT NULL,
    id_race INT NOT NULL,
    PRIMARY KEY(id_reputation_wiki),
    CONSTRAINT fk_reputation_wiki_race FOREIGN KEY(id_race) REFERENCES race(id_race)
    );

CREATE TABLE IF NOT EXISTS users (
    id_user INT AUTO_INCREMENT,
    pseudo VARCHAR(64) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY(id_user)
);

CREATE TABLE IF NOT EXISTS user_roles (
    id_user INT NOT NULL,
    role VARCHAR(32) NOT NULL,
    PRIMARY KEY (id_user, role),
    CONSTRAINT fk_user_roles_user FOREIGN KEY(id_user) REFERENCES users(id_user)
);

CREATE TABLE IF NOT EXISTS profession(
    id_profession INT AUTO_INCREMENT,
    nom VARCHAR(50) NOT NULL,
    description TEXT NOT NULL,
    vigueur INT,
    nb_objet INT NOT NULL DEFAULT 0,
    max_sort INT NOT NULL DEFAULT 0,
    max_rituel INT NOT NULL DEFAULT 0,
    max_envoutement INT NOT NULL DEFAULT 0,
    max_invocation INT NOT NULL DEFAULT 0,

    PRIMARY KEY(id_profession)
);

CREATE TABLE IF NOT EXISTS inventaire_wiki(
    id_inventaire_wiki INT AUTO_INCREMENT,
    quantite INT NOT NULL DEFAULT 1,
    nom VARCHAR(50) NOT NULL,
    type VARCHAR(10),
    effet TEXT,
    is_special BOOLEAN NOT NULL DEFAULT FALSE,
    id_profession INT NOT NULL,
    PRIMARY KEY(id_inventaire_wiki),
    CONSTRAINT fk_inventaire_wiki_profession FOREIGN KEY(id_profession) REFERENCES profession(id_profession)
);

CREATE TABLE IF NOT EXISTS competence_profession(
    id_profession INT NOT NULL,
    id_competence INT NOT NULL,
    PRIMARY KEY(id_profession, id_competence),
    CONSTRAINT fk_competence_profession_profession FOREIGN KEY(id_profession) REFERENCES profession(id_profession),
    CONSTRAINT fk_competence_profession_competence FOREIGN KEY(id_competence) REFERENCES competence(id_competence)
);

CREATE TABLE IF NOT EXISTS campagne(
    id_campagne INT AUTO_INCREMENT,
    nom VARCHAR(50) NOT NULL,
    id_user INT NOT NULL,
    PRIMARY KEY(id_campagne),
    CONSTRAINT fk_campagne_user FOREIGN KEY(id_user) REFERENCES users(id_user)
);

CREATE TABLE IF NOT EXISTS personnage(
    id_personnage INT AUTO_INCREMENT,
    nom_personnage VARCHAR(50) NOT NULL,
    nom_joueur VARCHAR(50),
    nom_image VARCHAR(100),
    url_image TEXT,
    genre CHAR(1),
    terre_natale VARCHAR(20),
    xp INT,
    age INT,
    bestiaire BOOLEAN default false,
    historique TEXT,
    poings VARCHAR(10),
    pieds VARCHAR(10),
    id_profession INT NOT NULL,
    id_inventaire INT,
    id_race INT NOT NULL,
    id_campagne INT,
    id_user INT,
    PRIMARY KEY(id_personnage),
    CONSTRAINT fk_personnage_profession FOREIGN KEY(id_profession) REFERENCES profession(id_profession),
    CONSTRAINT fk_personnage_race FOREIGN KEY(id_race) REFERENCES race(id_race),
    CONSTRAINT fk_personnage_campagne FOREIGN KEY(id_campagne) REFERENCES campagne(id_campagne),
    CONSTRAINT fk_personnage_user FOREIGN KEY(id_user) REFERENCES users(id_user)
);

CREATE TABLE IF NOT EXISTS inventaire(
    id_inventaire INT AUTO_INCREMENT,
    nom VARCHAR(60) NOT NULL,
    type VARCHAR(10),
    effet TEXT,
    quantite INT,
    id_personnage INT,
    PRIMARY KEY(id_inventaire),
    CONSTRAINT fk_inventaire_personnage FOREIGN KEY(id_personnage) REFERENCES personnage(id_personnage)
    );

CREATE TABLE IF NOT EXISTS caracteristique_personnage(
    id_personnage INT NOT NULL,
    id_caracteristique INT NOT NULL,
    valeur_actuelle INT NOT NULL,
    valeur_max INT NOT NULL,
    PRIMARY KEY(id_personnage, id_caracteristique),
    CONSTRAINT fk_caracteristique_personnage_personnage FOREIGN KEY(id_personnage) REFERENCES personnage(id_personnage),
    CONSTRAINT fk_caracteristique_personnage_caracteristique FOREIGN KEY(id_caracteristique) REFERENCES caracteristique(id_caracteristique)
);

CREATE TABLE IF NOT EXISTS reputation_personnalisee(
    id_reputation INT AUTO_INCREMENT,
    territoire VARCHAR(20) NOT NULL,
    valeur VARCHAR(20) NOT NULL,
    id_personnage INT NOT NULL,
    PRIMARY KEY(id_reputation),
    CONSTRAINT fk_reputation_personnage FOREIGN KEY(id_personnage) REFERENCES personnage(id_personnage)
);

CREATE TABLE IF NOT EXISTS competence_personnage(
    valeur_actuelle INT NOT NULL,
    valeur_max INT NOT NULL,
    id_personnage INT NOT NULL,
    id_competence INT NOT NULL,
    PRIMARY KEY(id_personnage, id_competence),
    CONSTRAINT fk_competence_personnage_personnage FOREIGN KEY(id_personnage) REFERENCES personnage(id_personnage),
    CONSTRAINT fk_competence_personnage_competence FOREIGN KEY(id_competence) REFERENCES competence(id_competence)
);

CREATE TABLE IF NOT EXISTS magie_personnage(
    id_personnage INT,
    id_magie INT,
    PRIMARY KEY(id_magie, id_personnage),
    CONSTRAINT fk_magie_personnage_personnage FOREIGN KEY(id_personnage) REFERENCES personnage(id_personnage),
    CONSTRAINT fk_magie_personnage_magie FOREIGN KEY(id_magie) REFERENCES magie(id_magie)
);

CREATE TABLE IF NOT EXISTS rituel_personnage(    id_personnage INT,
    id_rituel INT,
    PRIMARY KEY(id_rituel, id_personnage),
    CONSTRAINT fk_rituel_personnage_personnage FOREIGN KEY(id_personnage) REFERENCES personnage(id_personnage),
    CONSTRAINT fk_rituel_personnage_rituel FOREIGN KEY(id_rituel) REFERENCES rituel(id_rituel)
);

CREATE TABLE IF NOT EXISTS envoutement_personnage(
    id_personnage INT,
    id_envoutement INT,
    PRIMARY KEY(id_personnage, id_envoutement),
    CONSTRAINT fk_envoutement_personnage_personnage FOREIGN KEY(id_personnage) REFERENCES personnage(id_personnage),
    CONSTRAINT fk_envoutement_personnage_envoutement FOREIGN KEY(id_envoutement) REFERENCES envoutement(id_envoutement)
);
