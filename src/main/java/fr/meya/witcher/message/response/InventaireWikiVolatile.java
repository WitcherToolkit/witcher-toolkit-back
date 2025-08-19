package fr.meya.witcher.message.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventaireWikiVolatile {

    private Long idInventaireWiki;

    @NotNull(message = "error.inventaire.wiki.quantite.required")
    private int quantite;

    @NotBlank(message = "error.inventaire.wiki.nom.required")
    @Size( max = 50)
    private String nom;

    @Size( max = 10)
    private String type;

    private String effet;

    private boolean special;

}
