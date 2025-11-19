package fr.meya.witcher.message.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReputationWikiVolatile {

    private UUID idReputationWiki;

    @NotBlank(message = "error.reputation.wiki.territoire.required")
    @Size( max = 20)
    private String territoire;

    @NotBlank(message = "error.reputation.wiki.valeur.required")
    @Size( max = 20)
    private String valeur;

}
