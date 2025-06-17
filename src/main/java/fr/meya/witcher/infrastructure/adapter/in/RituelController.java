package fr.meya.witcher.infrastructure.adapter.in;

import fr.meya.witcher.application.mapper.RituelMapper;
import fr.meya.witcher.domain.model.persistent.Rituel;
import fr.meya.witcher.domain.port.in.IRituelService;
import fr.meya.witcher.message.response.RituelVolatile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/create")
    public ResponseEntity<Rituel> createRituel(@RequestBody RituelVolatile rituelVolatile) {
        log.info("Ajout d'un rituel");

        // Appel direct au service avec l'objet reçu
        Rituel createdRituel = iRituelService.createRituel(rituelVolatile);

        // Retourner la réponse
        return ResponseEntity.ok(createdRituel);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<RituelVolatile> updateRituel(@PathVariable Long id, @RequestBody RituelVolatile rituelVolatile) {
        log.info("modifier un rituel");
        Rituel updatedRituel = iRituelService.updateRituel(id, rituelVolatile);
        return ResponseEntity.ok(rituelMapper.toRituelDto(updatedRituel));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRituel(@PathVariable Long id) {
        log.info("Supprimer le rituel avec l'ID : {}", id);

        iRituelService.deleteRituel(id);
        return ResponseEntity.noContent().build();
    }
}
