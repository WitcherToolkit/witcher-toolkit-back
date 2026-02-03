package fr.meya.witcher.application.mapper;

import fr.meya.witcher.domain.model.persistent.Rituel;
import fr.meya.witcher.message.response.RituelVolatile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RituelMapper {

    RituelVolatile toRituelDto(Rituel rituel);

    Rituel toRituelEntity(RituelVolatile dto);

}