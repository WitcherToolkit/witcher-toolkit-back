package fr.meya.witcher.message.response;

import jakarta.validation.constraints.Size;

public class InventaireVolatile {

    private Long idInventaire;

    private int quantite;

    @Size( max = 50)
    private String nom;

    @Size( max = 10)
    private String type;

    private String effet;

}
