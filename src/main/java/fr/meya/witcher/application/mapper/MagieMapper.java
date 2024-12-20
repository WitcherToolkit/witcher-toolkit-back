package fr.meya.witcher.application.mapper;

import fr.meya.witcher.domain.model.persistent.Magie;
import fr.meya.witcher.message.response.MagieVolatile;
import org.springframework.stereotype.Component;

@Component
public class MagieMapper {
    // Convertir l'entité persistante en DTO
    public MagieVolatile toMagieDto(Magie magie) {
        return new MagieVolatile(
                magie.getNom(),
                magie.getCout(),
                magie.getEffet(),
                magie.getPortee(),
                magie.getDuree(),
                magie.getElement(),
                magie.getNiveau(),
                magie.getContre(),
                magie.getProfession()
        );
    }

    // Convertir un DTO en entité persistante
    public Magie toMagieEntity(MagieVolatile dto) {
        Magie magie = new Magie();
        magie.setNom(dto.getNom());
        magie.setCout(dto.getCout());
        magie.setEffet(dto.getEffet());
        magie.setPortee(dto.getPortee());
        magie.setDuree(dto.getDuree());
        magie.setElement(dto.getElement());
        magie.setNiveau(dto.getNiveau());
        magie.setContre(dto.getContre());
        magie.setProfession(dto.getProfession());
        return magie;
    }

}
