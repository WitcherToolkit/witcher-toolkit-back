package fr.meya.witcher.message.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessionVolatile {

    private Long idProfession;

    private String nom;

    private String description;

    private int vigueur;

    private int nbObjet;

    private int maxSort;

    private int maxRituel;

    private int maxEnvoutement;

    private int maxInvocation;

    private List<InventaireWikiVolatile> inventaireWikiList;

    private List<CompetenceProfessionVolatile> competenceList;

}
