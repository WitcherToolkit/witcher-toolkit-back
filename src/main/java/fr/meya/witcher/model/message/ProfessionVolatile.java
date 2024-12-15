package fr.meya.witcher.model.message;

import lombok.Data;

import java.util.List;

@Data
public class ProfessionVolatile {

    private long idProfession;

    private String nom;

    private String competenceExclusive;

    private String description;

    private  String codeCaracteristique;

    private List<CompetenceSpecifiqueVolatile> competenceSpecifique;

}
