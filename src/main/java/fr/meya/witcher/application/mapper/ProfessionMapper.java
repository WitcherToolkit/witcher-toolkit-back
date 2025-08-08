package fr.meya.witcher.application.mapper;

import fr.meya.witcher.domain.model.persistent.Profession;
import fr.meya.witcher.message.response.ProfessionVolatile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CompetenceProfessionMapper.class, InventaireWikiMapper.class})
public interface ProfessionMapper {

    @Mapping(target = "competenceList", source = "competenceProfessionList")
    @Mapping(target = "inventaireWikiList", source = "inventaireWikiList")
    ProfessionVolatile toProfessionDto(Profession profession);

    Profession toProfessionEntity(ProfessionVolatile dto);

}