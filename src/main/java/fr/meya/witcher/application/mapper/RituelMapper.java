package fr.meya.witcher.application.mapper;

import fr.meya.witcher.domain.model.persistent.Rituel;
import fr.meya.witcher.message.response.RituelVolatile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RituelMapper {
    // Convertir l'entité persistante en DTO
    public RituelVolatile toRituelDto(Rituel rituel);

    // Convertir un DTO en entité persistante
    public Rituel toRituelEntity(RituelVolatile dto);
}
