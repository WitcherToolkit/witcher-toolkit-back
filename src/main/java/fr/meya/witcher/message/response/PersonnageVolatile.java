package fr.meya.witcher.message.response;

import fr.meya.witcher.domain.model.persistent.CaracteristiquePersonnage;
import fr.meya.witcher.domain.model.persistent.Race;
import fr.meya.witcher.domain.model.persistent.User;
import jakarta.persistence.Column;

import java.util.List;

public class PersonnageVolatile {

    private long idPersonnage;

    private String nomJoueur;

    private String nomPersonnage;

    private String nomImage;

    private String urlImage;

    private String genre;

    private String terreNatale;

    private String xp;

    private long age;

    private boolean bestiaire;

    private String historique;

    private String poings;

    private String pieds;
    //----------------------------------------------------------------------------------------------------------------//

    private List<CompetencePersonnageVolatile> competencePersonnageList;

    private List<CaracteristiquePersonnageVolatile> caracteristiquePersonnageList;

    private List<InventaireVolatile> inventaireList;

    private Race race;

    private User user;

}
