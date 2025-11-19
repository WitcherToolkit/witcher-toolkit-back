package fr.meya.witcher.domain.port.in;

import fr.meya.witcher.domain.model.persistent.Rituel;
import fr.meya.witcher.message.response.RituelVolatile;

import java.util.List;
import java.util.UUID;

public interface IRituelService {
    boolean isValid(RituelVolatile rituelVolatile);

    List<RituelVolatile> getRituelList();

    Rituel getRituel(UUID idRituel);

    Rituel createRituel(RituelVolatile rituelVolatile);

    Rituel updateRituel(UUID idRituel, RituelVolatile rituelVolatile);

    void deleteRituel(UUID idRituel);
}
