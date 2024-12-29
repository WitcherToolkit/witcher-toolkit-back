package fr.meya.witcher.message.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompetenceSpecifiqueVolatile {

    private long idCompetenceSpecifique;

    private String nom;

    private String description;

    private String codeCaracteristique;

    private String specialisation;

    private String prerequis;

}
