package fr.meya.witcher.domain.port.in;

import fr.meya.witcher.domain.model.persistent.Profession;
import fr.meya.witcher.message.response.ProfessionVolatile;

import java.util.List;
import java.util.UUID;

public interface IProfessionService {

    boolean isValid(ProfessionVolatile professionVolatile);

    List<ProfessionVolatile> getProfessionList();

    Profession getProfession(UUID idProfession);

    ProfessionVolatile getProfessionWithCompetences(UUID idProfession);

    Profession createProfession(ProfessionVolatile professionVolatile);

    Profession updateProfession(UUID idProfession, ProfessionVolatile professionVolatile);

    void deleteProfession(UUID idProfession);

}
