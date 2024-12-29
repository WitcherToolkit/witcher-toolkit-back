package fr.meya.witcher.domain.port.in;

import fr.meya.witcher.domain.model.persistent.Envoutement;
import fr.meya.witcher.message.response.EnvoutementVolatile;

import java.util.List;

public interface IEnvoutementService {

    boolean isValid(EnvoutementVolatile envoutementVolatile);

    List<EnvoutementVolatile> getEnvoutementList();

    Envoutement getEnvoutement(Long idEnvoutement);

    Envoutement createEnvoutement(EnvoutementVolatile envoutementVolatile);

    Envoutement updateEnvoutement(Long idEnvoutement, EnvoutementVolatile envoutementVolatile);

    void deleteEnvoutement(Long idEnvoutement);
}
