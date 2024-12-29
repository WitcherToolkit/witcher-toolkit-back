package fr.meya.witcher.domain.port.in;

import fr.meya.witcher.domain.model.persistent.Profession;
import fr.meya.witcher.message.response.ProfessionVolatile;

import java.util.List;

public interface IProfessionService {

    boolean isValid(ProfessionVolatile professionVolatile);

    List<ProfessionVolatile> getProfessionList();

    Profession getProfession(Long idProfession);

    Profession createProfession(ProfessionVolatile professionVolatile);

    Profession updateProfession(Long idProfession, ProfessionVolatile professionVolatile);

    void deleteProfession(Long idProfession);

}
