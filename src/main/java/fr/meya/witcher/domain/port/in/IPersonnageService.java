package fr.meya.witcher.domain.port.in;

import fr.meya.witcher.domain.model.persistent.Personnage;
import fr.meya.witcher.message.response.PersonnageVolatile;

import java.util.List;

public interface IPersonnageService {

    boolean isValid(PersonnageVolatile personnageVolatile);

    List<PersonnageVolatile> getPersonnageList();

    Personnage getPersonnage(Long idPersonnage);

    Personnage createPersonnage(PersonnageVolatile personnageVolatile);

    Personnage updatePersonnage(Long idPersonnage, PersonnageVolatile personnageVolatile);

    void deletePersonnage(Long idPersonnage);
}

