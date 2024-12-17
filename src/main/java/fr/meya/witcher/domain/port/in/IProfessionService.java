package fr.meya.witcher.domain.port.in;

import fr.meya.witcher.message.response.ProfessionVolatile;

import java.util.List;

public interface IProfessionService {

    List<ProfessionVolatile> getListeDesProfessionsVolatiles();

}
