-- TABLE DE BASE : Profession
CREATE TABLE Profession (
                            idProfession BIGINT AUTO_INCREMENT PRIMARY KEY,
                            nom VARCHAR(255) NOT NULL
);

-- TABLE DE BASE : Personnage
CREATE TABLE Personnage (
                            idPersonnage BIGINT AUTO_INCREMENT PRIMARY KEY,
                            nomJoueur VARCHAR(255),
                            nomPersonnage VARCHAR(255),
                            nomImage VARCHAR(255),
                            urlImage VARCHAR(255),
                            genre VARCHAR(50),
                            terreNatale VARCHAR(50),
                            xp VARCHAR(50),
                            age BIGINT,
                            isBestiaire BOOLEAN,
                            idProfessionPersonnage BIGINT,
                            idRace BIGINT NOT NULL,
                            idCampagne BIGINT,
                            idUser BIGINT
);

-- TABLE DE BASE : Race
CREATE TABLE Race (
                      idRace BIGINT AUTO_INCREMENT PRIMARY KEY,
                      nom VARCHAR(255) NOT NULL
);

-- TABLE DE BASE : Campagne
CREATE TABLE Campagne (
                          idCampagne BIGINT AUTO_INCREMENT PRIMARY KEY,
                          nom VARCHAR(255) NOT NULL
);

-- TABLE DE BASE : User
CREATE TABLE User (
                      idUser BIGINT AUTO_INCREMENT PRIMARY KEY,
                      username VARCHAR(255) NOT NULL
);

-- TABLE DE LIEN : ProfessionPersonnage
CREATE TABLE ProfessionPersonnage (
                                      idProfessionPersonnage BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      professionName VARCHAR(255) NOT NULL
);

-- TABLE DE BASE : CompetenceSpecifique
CREATE TABLE CompetenceSpecifique (
                                      idCompetenceSpecifique BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      nom VARCHAR(255) NOT NULL,
                                      description TEXT NOT NULL,
                                      codeCaracteristique VARCHAR(50) NOT NULL,
                                      specialisation VARCHAR(255) NOT NULL,
                                      presrequis VARCHAR(255) NOT NULL,
                                      idProfession BIGINT,
                                      FOREIGN KEY (idProfession) REFERENCES Profession(idProfession)
);

-- TABLE DE LIEN : CompetenceSpecifiquePersonnage
CREATE TABLE CompetenceSpecifiquePersonnage (
                                                idCompetenceSpecifiquePersonnage BIGINT AUTO_INCREMENT PRIMARY KEY,
                                                valeurActuelle INT,
                                                valeurMax INT,
                                                idProfession BIGINT NOT NULL,
                                                idCompetenceSpecifique BIGINT NOT NULL,
                                                idPersonnage BIGINT NOT NULL,
                                                FOREIGN KEY (idProfession) REFERENCES Profession(idProfession),
                                                FOREIGN KEY (idCompetenceSpecifique) REFERENCES CompetenceSpecifique(idCompetenceSpecifique),
                                                FOREIGN KEY (idPersonnage) REFERENCES Personnage(idPersonnage)
);

-- RELATION MANY-TO-MANY : Rituel et Personnage
CREATE TABLE RituelPersonnage (
                                  idRituel BIGINT NOT NULL,
                                  idPersonnage BIGINT NOT NULL,
                                  PRIMARY KEY (idRituel, idPersonnage),
                                  FOREIGN KEY (idRituel) REFERENCES Rituel(idRituel),
                                  FOREIGN KEY (idPersonnage) REFERENCES Personnage(idPersonnage)
);

-- RELATION MANY-TO-MANY : Envoutement et Personnage
CREATE TABLE EnvoutementPersonnage (
                                       idEnvoutement BIGINT NOT NULL,
                                       idPersonnage BIGINT NOT NULL,
                                       PRIMARY KEY (idEnvoutement, idPersonnage),
                                       FOREIGN KEY (idEnvoutement) REFERENCES Envoutement(idEnvoutement),
                                       FOREIGN KEY (idPersonnage) REFERENCES Personnage(idPersonnage)
);

-- TABLE DE BASE (EXEMPLE) : Rituel
CREATE TABLE Rituel (
                        idRituel BIGINT AUTO_INCREMENT PRIMARY KEY,
                        nom VARCHAR(255) NOT NULL
);

-- TABLE DE BASE (EXEMPLE) : Envoutement
CREATE TABLE Envoutement (
                             idEnvoutement BIGINT AUTO_INCREMENT PRIMARY KEY,
                             nom VARCHAR(255) NOT NULL
);