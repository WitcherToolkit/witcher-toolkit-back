package fr.meya.witcher.application.service;

import fr.meya.witcher.domain.model.persistent.Personnage;
import fr.meya.witcher.domain.port.in.IPersonnageService;
import fr.meya.witcher.message.response.PersonnageVolatile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonnageService implements IPersonnageService {

    @Override
    public List<PersonnageVolatile> getPersonnageList() {
        return List.of();
    }

    @Override
    public Personnage getPersonnage(Long idPersonnage) {
        return null;
    }

    @Override
    public Personnage createPersonnage(PersonnageVolatile ersonnageVolatile) {
        return null;
    }

    @Override
    public Personnage updatePersonnage(Long idPersonnage, PersonnageVolatile personnageVolatile) {
        return null;
    }

    @Override
    public void deletePersonnage(Long idPersonnage) {

    }
}
