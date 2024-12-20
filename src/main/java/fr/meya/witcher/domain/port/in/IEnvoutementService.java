package fr.meya.witcher.domain.port.in;

import fr.meya.witcher.domain.model.persistent.Envoutement;
import fr.meya.witcher.message.response.EnvoutementVolatile;

import java.util.List;

public interface IEnvoutementService {

    boolean isValid(EnvoutementVolatile envoutementVolatile);

    Envoutement createEnvoutement(EnvoutementVolatile envoutementVolatile);

    Envoutement getEnvoutement(Long idEnvoutement);

    Envoutement deleteEnvoutement(Long idEnvoutement);

    Envoutement updateEnvoutement(Long idEnvoutement, EnvoutementVolatile envoutementVolatile);

    List<EnvoutementVolatile> getEnvoutementList();
}
