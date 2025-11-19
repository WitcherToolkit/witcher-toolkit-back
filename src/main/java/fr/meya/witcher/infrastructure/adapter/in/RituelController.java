package fr.meya.witcher.infrastructure.adapter.in;

import fr.meya.witcher.application.mapper.RituelMapper;
import fr.meya.witcher.domain.model.persistent.Rituel;
import fr.meya.witcher.domain.port.in.IRituelService;
import fr.meya.witcher.message.response.RituelVolatile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/rituels")
public class RituelController {
    private final IRituelService iRituelService;
    private final RituelMapper rituelMapper;

    public RituelController(IRituelService iRituelService, RituelMapper rituelMapper) {
        this.iRituelService = iRituelService;
        this.rituelMapper = rituelMapper;
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<RituelVolatile>> listRituel() {
        log.info("consultation rituel");
        List<RituelVolatile> result = iRituelService.getRituelList();
        return ResponseEntity.ok(result);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<RituelVolatile> updateRituel(@PathVariable UUID id, @RequestBody RituelVolatile rituelVolatile) {
        log.info("Modification du rituel - ID : {} - Données : {}", id, rituelVolatile);
        Rituel updatedRituel = iRituelService.updateRituel(id, rituelVolatile);

        return ResponseEntity.ok(rituelMapper.toRituelDto(updatedRituel));
    }

    @PostMapping("/create")
    public ResponseEntity<Rituel> createRituel(@RequestBody RituelVolatile rituelVolatile) {
        log.info("Ajout d'un rituel");

        // Appel direct au service avec l'objet reçu
        Rituel createdRituel = iRituelService.createRituel(rituelVolatile);

        // Retourner la réponse
        return ResponseEntity.ok(createdRituel);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRituel(@PathVariable UUID id) {
        log.info("Supprimer le rituel avec l'ID : {}", id);

        iRituelService.deleteRituel(id);
        return ResponseEntity.noContent().build();
    }
}
