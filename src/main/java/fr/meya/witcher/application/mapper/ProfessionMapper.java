package fr.meya.witcher.application.mapper;

import fr.meya.witcher.domain.model.persistent.Profession;
import fr.meya.witcher.message.response.ProfessionVolatile;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring",
        uses = {CompetenceProfessionMapper.class, InventaireWikiMapper.class},
        collectionMappingStrategy = CollectionMappingStrategy.SETTER_PREFERRED
)
public interface ProfessionMapper {

    @Mapping(target = "competenceList", source = "competenceProfessionList")
    @Mapping(target = "inventaireWikiList", source = "inventaireWikiList")
    ProfessionVolatile toProfessionDto(Profession profession);

    Profession toProfessionEntity(ProfessionVolatile dto);

    //Méthode pour mise à jour partielle d'une entité existante
    @Mapping(target = "competenceProfessionList", source = "competenceList")
    @Mapping(target = "inventaireWikiList", source = "inventaireWikiList")
    void updateProfessionFromVolatile(ProfessionVolatile dto, @MappingTarget Profession entity);

}