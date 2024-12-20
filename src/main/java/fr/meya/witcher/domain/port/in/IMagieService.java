package fr.meya.witcher.domain.port.in;

import fr.meya.witcher.domain.model.persistent.Magie;
import fr.meya.witcher.message.response.MagieVolatile;

import java.util.List;

public interface IMagieService {

    boolean isValid(MagieVolatile magieVolatile);

    List<MagieVolatile> getMagieList();

    Magie getMagie(Long idMagie);

    Magie createMagie(MagieVolatile magieVolatile);

    Magie updateMagie(Long idMagie, MagieVolatile magieVolatile);

    void deleteMagie(Long idMagie);
}
