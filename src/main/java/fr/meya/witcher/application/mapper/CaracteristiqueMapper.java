package fr.meya.witcher.application.mapper;

import fr.meya.witcher.domain.model.persistent.Caracteristique;
import fr.meya.witcher.message.response.CaracteristiqueVolatile;
import org.springframework.stereotype.Component;

@Component
public class CaracteristiqueMapper {

    // Convertir l'entité persistante en DTO
    public CaracteristiqueVolatile toCaracteristiqueDto(Caracteristique caracteristique) {
        return new CaracteristiqueVolatile(
                caracteristique.getIdCaracteristique(),
                caracteristique.getNom(),
                caracteristique.getCode(),
                caracteristique.getDescription()
        );
    }

    // Convertir un DTO en entité persistante
    public Caracteristique toCaracteristiqueEntity(CaracteristiqueVolatile dto) {
        Caracteristique caracteristique = new Caracteristique();
        caracteristique.setIdCaracteristique(dto.getIdCaracteristique());
        caracteristique.setNom(dto.getNom());
        caracteristique.setCode(dto.getCode());
        caracteristique.setDescription(dto.getDescription());
        return caracteristique;
    }

}
