package fr.meya.witcher.domain.port.in;

import fr.meya.witcher.domain.model.persistent.Race;
import fr.meya.witcher.message.response.RaceVolatile;

import java.util.List;
import java.util.UUID;

public interface IRaceService {

    boolean isValid(RaceVolatile raceVolatile);

    List<RaceVolatile> getRaceList();

    Race getRaceById(UUID idRace);

    Race createRace(RaceVolatile raceVolatile);

    Race updateRace(UUID idRace, RaceVolatile raceVolatile);

    void deleteRace(UUID idRace);
}
