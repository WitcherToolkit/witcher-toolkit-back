package fr.meya.witcher.domain.port.in;

import fr.meya.witcher.domain.model.persistent.Race;
import fr.meya.witcher.message.response.RaceVolatile;

import java.util.List;

public interface IRaceService {

    boolean isValid(RaceVolatile raceVolatile);

    List<RaceVolatile> getRaceList();

    Race getRace(Long idRace);

    Race createRace(RaceVolatile raceVolatile);

    Race updateRace(Long idRace, RaceVolatile raceVolatile);

    void deleteRace(Long idRace);
}
