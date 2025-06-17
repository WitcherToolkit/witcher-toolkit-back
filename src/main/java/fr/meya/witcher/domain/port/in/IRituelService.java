package fr.meya.witcher.domain.port.in;

import fr.meya.witcher.domain.model.persistent.Rituel;
import fr.meya.witcher.message.response.RituelVolatile;

import java.util.List;

public interface IRituelService {
    boolean isValid(RituelVolatile rituelVolatile);

    List<RituelVolatile> getRituelList();

    Rituel getRituel(Long idRituel);

    Rituel createRituel(RituelVolatile rituelVolatile);

    Rituel updateRituel(Long idRituel, RituelVolatile rituelVolatile);

    void deleteRituel(Long idRituel);
}
