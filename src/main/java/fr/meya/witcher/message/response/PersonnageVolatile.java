package fr.meya.witcher.message.response;

import fr.meya.witcher.domain.model.persistent.Race;
import fr.meya.witcher.domain.model.persistent.RituelPersonnage;
import fr.meya.witcher.domain.model.persistent.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
    private RaceVolatile race;

    private UserVolatile user;

    private List<CaracteristiquePersonnageVolatile> caracteristiquePersonnageList;

    private List<CompetencePersonnageVolatile> competencePersonnageList;

    private List<RituelVolatile> rituelPersonnageList;

    private  List<EnvoutementVolatile> envoutementList;

    private List<InventaireVolatile> inventaireList;



}
