package fr.meya.witcher.message.response;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventaireVolatile {

    private UUID idInventaire;

    private int quantite;

    @Size( max = 50)
    private String nom;

    @Size( max = 10)
    private String type;

    private String effet;

}
