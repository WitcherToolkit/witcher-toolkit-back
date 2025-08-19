package fr.meya.witcher.message.response;

import fr.meya.witcher.domain.model.persistent.Race;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReputationWikiVolatile {

    private Long idReputationWiki;

    @NotBlank(message = "error.reputation.wiki.territoire.required")
    @Size( max = 20)
    private String territoire;

    @NotBlank(message = "error.reputation.wiki.valeur.required")
    @Size( max = 20)
    private String valeur;

}
