package fr.meya.witcher.model.message;

import lombok.Data;

@Data
public class CompetenceSpecifiqueVolatile {

    private long idCompetenceSpecifique;

    private String nom;

    private String description;

    private String codeCaracteristique;

    private String specialisation;

    private String prerequis;

}
