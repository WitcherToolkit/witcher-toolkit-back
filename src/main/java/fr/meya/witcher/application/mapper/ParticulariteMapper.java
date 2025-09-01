package fr.meya.witcher.application.mapper;

import fr.meya.witcher.domain.model.persistent.Particularite;
import fr.meya.witcher.domain.model.persistent.Race;
import fr.meya.witcher.message.response.ParticulariteVolatile;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ParticulariteMapper {

    ParticulariteVolatile toDto(Particularite entity);

    Particularite toEntity(ParticulariteVolatile dto);

    @AfterMapping
    default void linkRace(@MappingTarget Particularite entity, Race race) {
        if (entity != null) {
            entity.setRace(race);
        }
    }
}
