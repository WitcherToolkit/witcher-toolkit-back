package fr.meya.witcher.message.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventaireWikiVolatile {

    private Long idInventaireWiki;

    private String nom;

    private String type;

    private String effet;

    private boolean special;

}
