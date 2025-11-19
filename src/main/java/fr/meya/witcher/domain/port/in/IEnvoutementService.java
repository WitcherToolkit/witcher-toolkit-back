package fr.meya.witcher.domain.port.in;

import fr.meya.witcher.domain.model.persistent.Envoutement;
import fr.meya.witcher.message.response.EnvoutementVolatile;

import java.util.List;
import java.util.UUID;

public interface IEnvoutementService {

    boolean isValid(EnvoutementVolatile envoutementVolatile);

    List<EnvoutementVolatile> getEnvoutementList();

    Envoutement getEnvoutement(UUID idEnvoutement);

    Envoutement createEnvoutement(EnvoutementVolatile envoutementVolatile);

    Envoutement updateEnvoutement(UUID idEnvoutement, EnvoutementVolatile envoutementVolatile);

    void deleteEnvoutement(UUID idEnvoutement);
}
