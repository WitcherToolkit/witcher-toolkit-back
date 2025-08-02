package fr.meya.witcher.application.mapper;

import fr.meya.witcher.domain.model.persistent.Envoutement;
import fr.meya.witcher.message.response.EnvoutementVolatile;
import org.springframework.stereotype.Component;

@Component
public class EnvoutementMapper {
    // Convertir l'entité persistante en DTO
    public EnvoutementVolatile toEnvoutementDto(Envoutement envoutement) {
        return new EnvoutementVolatile(
                envoutement.getIdEnvoutement(),
                envoutement.getNom(),
                envoutement.getCout(),
                envoutement.getEffet(),
                envoutement.getPrerequis(),
                envoutement.getDanger()
        );
    }

    // Convertir un DTO en entité persistante
    public Envoutement toEnvoutementEntity(EnvoutementVolatile dto) {
        Envoutement envoutement = new Envoutement();
        envoutement.setIdEnvoutement(dto.getIdEnvoutement());
        envoutement.setNom(dto.getNom());
        envoutement.setCout(dto.getCout());
        envoutement.setEffet(dto.getEffet());
        envoutement.setPrerequis(dto.getPrerequis());
        envoutement.setDanger(dto.getDanger());
        return envoutement;
    }

}
