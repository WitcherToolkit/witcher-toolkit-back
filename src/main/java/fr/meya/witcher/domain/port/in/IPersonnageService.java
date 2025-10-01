package fr.meya.witcher.domain.port.in;

import fr.meya.witcher.domain.model.persistent.Personnage;
import fr.meya.witcher.message.response.PersonnageVolatile;

import java.util.List;

public interface IPersonnageService {

    boolean isValid(PersonnageVolatile personnageVolatile);

    List<PersonnageVolatile> getPersonnageList();

    PersonnageVolatile getPersonnage(Long idPersonnage);

    PersonnageVolatile createPersonnage(PersonnageVolatile personnageVolatile);

    PersonnageVolatile updatePersonnage(Long idPersonnage, PersonnageVolatile personnageVolatile);

    void deletePersonnage(Long idPersonnage);
}

