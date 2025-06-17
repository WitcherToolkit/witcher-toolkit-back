package fr.meya.witcher.application.mapper;

import fr.meya.witcher.domain.model.persistent.Magie;
import fr.meya.witcher.message.response.MagieVolatile;
import org.springframework.stereotype.Component;

@Component
public class MagieMapper {
    // Convertir l'entité persistante en DTO
    public MagieVolatile toMagieDto(Magie magie) {
        return new MagieVolatile(
                magie.getIdMagie(),
                magie.getNom(),
                magie.getCout(),
                magie.getEffet(),
                magie.getPortee(),
                magie.getDuree(),
                magie.getNature(),
                magie.getType(),
                magie.getNiveau(),
                magie.getContre()
        );
    }

    // Convertir un DTO en entité persistante
    public Magie toMagieEntity(MagieVolatile dto) {
        Magie magie = new Magie();
        magie.setIdMagie(dto.getIdMagie());
        magie.setNom(dto.getNom());
        magie.setCout(dto.getCout());
        magie.setEffet(dto.getEffet());
        magie.setPortee(dto.getPortee());
        magie.setDuree(dto.getDuree());
        magie.setNature(dto.getNature());
        magie.setType(dto.getType());
        magie.setNiveau(dto.getNiveau());
        magie.setContre(dto.getContre());
        return magie;
    }

}
