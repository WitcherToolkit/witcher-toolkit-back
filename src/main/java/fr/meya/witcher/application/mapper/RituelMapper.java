package fr.meya.witcher.application.mapper;

import fr.meya.witcher.domain.model.persistent.Rituel;
import fr.meya.witcher.message.response.RituelVolatile;
import org.springframework.stereotype.Component;

@Component
public class RituelMapper {
    // Convertir l'entité persistante en DTO
    public RituelVolatile toRituelDto(Rituel rituel) {
        return new RituelVolatile(
                rituel.getIdRituel(),
                rituel.getNom(),
                rituel.getCout(),
                rituel.getEffet(),
                rituel.getTempsPreparation(),
                rituel.getSd(),
                rituel.getDuree(),
                rituel.getComposant(),
                rituel.getNiveau()
        );
    }

    // Convertir un DTO en entité persistante
    public Rituel toRituelEntity(RituelVolatile dto) {
        Rituel rituel = new Rituel();
        rituel.setIdRituel(dto.getIdRituel());
        rituel.setNom(dto.getNom());
        rituel.setCout(dto.getCout());
        rituel.setEffet(dto.getEffet());
        rituel.setTempsPreparation(dto.getTempsPreparation());
        rituel.setSd(dto.getSd());
        rituel.setDuree(dto.getDuree());
        rituel.setComposant(dto.getComposant());
        rituel.setNiveau(dto.getNiveau());
        return rituel;
    }
}
