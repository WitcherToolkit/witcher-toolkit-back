package fr.meya.witcher.infrastructure.adapter.in;

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

    public EnvoutementController(IEnvoutementService iEnvoutementService) {
        this.iEnvoutementService = iEnvoutementService;
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<EnvoutementVolatile>> listEnvoutement() {
        log.info("consultation envoutement");
        List<EnvoutementVolatile> resul = iEnvoutementService.getEnvoutementList();
        return ResponseEntity.ok(resul);
    }

    @GetMapping(value = "/list/{id}")
    public Envoutement getEnvoutement(@PathVariable Long id) {
        log.info("consultation envoutement");
        return iEnvoutementService.getEnvoutement(id);
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
    public ResponseEntity<Envoutement> deleteEnvoutement(@PathVariable Long id) {
        log.info("Supprimer l'envoutement avec l'ID : {}", id);
        Envoutement deletedEnvoutement = iEnvoutementService.deleteEnvoutement(id);
        // Retourne l'envoutement supprimé dans la réponse
        return ResponseEntity.ok(deletedEnvoutement);
    }
    
}
