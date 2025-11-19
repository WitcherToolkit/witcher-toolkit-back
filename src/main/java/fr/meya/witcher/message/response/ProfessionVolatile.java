package fr.meya.witcher.message.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessionVolatile {

    private UUID idProfession;

    @NotBlank(message = "error.profession.nom.required")
    @Size( max = 50)
    private String nom;

    @NotBlank(message = "error.profession.description.required")
    private String description;

    @NotNull(message = "error.profession.vigueur.required")
    private int vigueur;

    @NotNull(message = "error.profession.objet.required")
    private int nbObjet;

    @NotNull(message = "error.profession.max.sort.required")
    private int maxSort;

    @NotNull(message = "error.profession.max.rituel.required")
    private int maxRituel;

    @NotNull(message = "error.profession.max.envoutement.required")
    private int maxEnvoutement;

    @NotNull(message = "error.profession.max.invocation.required")
    private int maxInvocation;

    private List<InventaireWikiVolatile> inventaireWikiList;

    private List<CompetenceProfessionVolatile> competenceList;

}
