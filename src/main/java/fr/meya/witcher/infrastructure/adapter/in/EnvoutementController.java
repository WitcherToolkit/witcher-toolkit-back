package fr.meya.witcher.infrastructure.adapter.in;

import fr.meya.witcher.application.mapper.EnvoutementMapper;
import fr.meya.witcher.domain.model.persistent.Envoutement;
import fr.meya.witcher.domain.port.in.IEnvoutementService;
import fr.meya.witcher.message.response.EnvoutementVolatile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/envoutement")
public class EnvoutementController {
    private final IEnvoutementService iEnvoutementService;
    private final EnvoutementMapper envoutementMapper;

    public EnvoutementController(IEnvoutementService iEnvoutementService, EnvoutementMapper envoutementMapper) {
        this.iEnvoutementService = iEnvoutementService;
        this.envoutementMapper = envoutementMapper;
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<EnvoutementVolatile>> listEnvoutement() {
        log.info("consultation envoutement");
        List<EnvoutementVolatile> resul = iEnvoutementService.getEnvoutementList();
        return ResponseEntity.ok(resul);
    }

    @PostMapping("/create")
    public ResponseEntity<Envoutement> createEnvoutement(@RequestBody EnvoutementVolatile envoutementVolatile) {
        log.info("Créer une envoutement");

        // Appel direct au service avec l'objet reçu
        Envoutement createdEnvoutement = iEnvoutementService.createEnvoutement(envoutementVolatile);

        // Retourner la réponse
        return ResponseEntity.ok(createdEnvoutement);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Envoutement> updateEnvoutement(@PathVariable Long id, @RequestBody EnvoutementVolatile envoutementVolatile) {
        log.info("modifier une envoutement");
        Envoutement updatedEnvoutement = iEnvoutementService.updateEnvoutement(id, envoutementVolatile);
        return ResponseEntity.ok(updatedEnvoutement);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEnvoutement(@PathVariable Long id) {
        log.info("Supprimer l'envoûtement avec l'ID : {}", id);

        iEnvoutementService.deleteEnvoutement(id);
        return ResponseEntity.noContent().build();
    }
    
}
