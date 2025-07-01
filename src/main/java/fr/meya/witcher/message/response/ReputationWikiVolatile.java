package fr.meya.witcher.message.response;

import fr.meya.witcher.domain.model.persistent.Race;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReputationWikiVolatile {

    private Long idReputationWiki;

    private String territoire;

    private String valeur;

}
