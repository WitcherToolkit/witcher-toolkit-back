package fr.meya.witcher.message.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessionVolatile {

    private String nom;

    private String competenceExclusive;

    private String description;

    private  String codeCaracteristique;

    //private List<CompetenceSpecifiqueVolatile> competenceSpecifique;

}
