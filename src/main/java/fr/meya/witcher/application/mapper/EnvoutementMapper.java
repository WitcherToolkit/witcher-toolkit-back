package fr.meya.witcher.application.mapper;

import fr.meya.witcher.domain.model.persistent.Envoutement;
import fr.meya.witcher.message.response.EnvoutementVolatile;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface EnvoutementMapper {
    // Convertir l'entité persistante en DTO
    EnvoutementVolatile toEnvoutementDto(Envoutement envoutement);

    // Convertir un DTO en entité persistante
    Envoutement toEnvoutementEntity(EnvoutementVolatile dto);

}
