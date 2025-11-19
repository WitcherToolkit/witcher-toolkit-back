package fr.meya.witcher.domain.port.in;

import fr.meya.witcher.domain.model.persistent.Magie;
import fr.meya.witcher.message.response.MagieVolatile;

import java.util.List;
import java.util.UUID;

public interface IMagieService {

    boolean isValid(MagieVolatile magieVolatile);

    List<MagieVolatile> getMagieList(String niveau);

    Magie getMagie(UUID idMagie);

    Magie createMagie(MagieVolatile magieVolatile);

    Magie updateMagie(UUID idMagie, MagieVolatile magieVolatile);

    void deleteMagie(UUID idMagie);
}
