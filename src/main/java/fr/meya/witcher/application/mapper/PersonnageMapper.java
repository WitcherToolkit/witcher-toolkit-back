package fr.meya.witcher.application.mapper;

import fr.meya.witcher.domain.model.persistent.EnvoutementPersonnage;
import fr.meya.witcher.domain.model.persistent.MagiePersonnage;
import fr.meya.witcher.domain.model.persistent.Personnage;
import fr.meya.witcher.domain.model.persistent.RituelPersonnage;
import fr.meya.witcher.message.response.EnvoutementVolatile;
import fr.meya.witcher.message.response.MagieVolatile;
import fr.meya.witcher.message.response.PersonnageVolatile;
import fr.meya.witcher.message.response.RituelVolatile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(
        componentModel = "spring",
        uses = {
                UserMapper.class,
                RaceMapper.class,
                ProfessionMapper.class,
                InventaireMapper.class,
                CaracteristiquePersonnageMapper.class,
                CompetencePersonnageMapper.class,
                RituelMapper.class,
                EnvoutementMapper.class,
                PersonnageMapper.class
        }
)
public interface PersonnageMapper {

    @Mapping(target = "rituelList", expression = "java(mapRituels(personnage.getRituelPersonnageList()))")
    @Mapping(target = "envoutementList", expression = "java(mapEnvoutements(personnage.getEnvoutementPersonnageList()))")
    @Mapping(target = "magieList", expression = "java(mapMagies(personnage.getMagiePersonnageList()))")
    PersonnageVolatile toPersonnageDto(Personnage personnage);

    @Mapping(target = "race", ignore = true)
    @Mapping(target = "profession", ignore = true)
    @Mapping(target = "rituelPersonnageList", ignore = true)
    @Mapping(target = "envoutementPersonnageList", ignore = true)
    @Mapping(target = "magiePersonnageList", ignore = true)
    Personnage toPersonnageEntity(PersonnageVolatile dto);

    // Mapping des rituels
    default List<RituelVolatile> mapRituels(List<RituelPersonnage> rituelPersonnageList) {
        if (rituelPersonnageList == null) {
            return null;
        }
        RituelMapper rituelMapper = org.mapstruct.factory.Mappers.getMapper(RituelMapper.class);
        return rituelPersonnageList.stream()
                .filter(rp -> rp.getRituel() != null)
                .map(rp -> rituelMapper.toRituelDto(rp.getRituel()))
                .collect(Collectors.toList());
    }

    // Mapping des envoutements
    default List<EnvoutementVolatile> mapEnvoutements(List<EnvoutementPersonnage> envoutementPersonnageList) {
        if (envoutementPersonnageList == null) {
            return null;
        }
        EnvoutementMapper envoutementMapper = org.mapstruct.factory.Mappers.getMapper(EnvoutementMapper.class);
        return envoutementPersonnageList.stream()
                .filter(ep -> ep.getEnvoutement() != null)
                .map(ep -> envoutementMapper.toEnvoutementDto(ep.getEnvoutement()))
                .collect(Collectors.toList());
    }

    // Mapping des magies
    default List<MagieVolatile> mapMagies(List<MagiePersonnage> magiePersonnageList) {
        if (magiePersonnageList == null) {
            return null;
        }
        MagieMapper magieMapper = org.mapstruct.factory.Mappers.getMapper(MagieMapper.class);
        return magiePersonnageList.stream()
                .filter(mp -> mp != null && mp.getMagie() != null)
                .map(mp -> magieMapper.toMagieDto(mp.getMagie()))
                .collect(Collectors.toList());
    }
}
