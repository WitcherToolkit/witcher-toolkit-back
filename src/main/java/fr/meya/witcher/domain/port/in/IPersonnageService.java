package fr.meya.witcher.domain.port.in;

import fr.meya.witcher.domain.model.persistent.Personnage;
import fr.meya.witcher.message.response.MagieVolatile;
import fr.meya.witcher.message.response.PersonnageVolatile;

import java.util.List;

public interface IPersonnageService {

    List<PersonnageVolatile> getPersonnageList();

    Personnage getPersonnage(Long idPersonnage);

    Personnage createPersonnage(PersonnageVolatile ersonnageVolatile);

    Personnage updatePersonnage(Long idPersonnage, PersonnageVolatile personnageVolatile);

    void deletePersonnage(Long idPersonnage);
}
