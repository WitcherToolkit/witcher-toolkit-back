package fr.meya.witcher.domain.port.in;

import fr.meya.witcher.domain.model.persistent.Personnage;
import fr.meya.witcher.message.response.PersonnageVolatile;

import java.util.List;
import java.util.UUID;

public interface IPersonnageService {

    boolean isValid(PersonnageVolatile personnageVolatile);

    List<PersonnageVolatile> getPersonnageList();

    PersonnageVolatile getPersonnage(UUID idPersonnage);

    PersonnageVolatile createPersonnage(PersonnageVolatile personnageVolatile);

    PersonnageVolatile updatePersonnage(UUID idPersonnage, PersonnageVolatile personnageVolatile);

    void deletePersonnage(UUID idPersonnage);
}

