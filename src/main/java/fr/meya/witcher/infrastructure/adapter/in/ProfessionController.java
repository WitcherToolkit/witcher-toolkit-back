package fr.meya.witcher.infrastructure.adapter.in;

import fr.meya.witcher.application.mapper.ProfessionMapper;
import fr.meya.witcher.domain.model.persistent.Profession;
import fr.meya.witcher.domain.port.in.IProfessionService;
import fr.meya.witcher.message.response.ProfessionVolatile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/profession")
public class ProfessionController {

    private final IProfessionService iProfessionService;
    private final ProfessionMapper professionMapper;

    public ProfessionController(IProfessionService iProfessionService, ProfessionMapper professionMapper) {
        this.iProfessionService = iProfessionService;
        this.professionMapper = professionMapper;
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<ProfessionVolatile>> listProfession() {
        log.info("consultation profession");
        List<ProfessionVolatile> result = iProfessionService.getProfessionList();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/create")
    public ResponseEntity<Profession> createProfession(@RequestBody ProfessionVolatile professionVolatile) {
        log.info("Ajout d'une profession");

        // Appel direct au service avec l'objet reçu
        Profession createdProfession = iProfessionService.createProfession(professionVolatile);

        // Retourner la réponse
        return ResponseEntity.ok(createdProfession);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<ProfessionVolatile> updateProfession(@PathVariable Long id, @RequestBody ProfessionVolatile professionVolatile) {
        log.info("modifier une profession");
        Profession updatedProfession = iProfessionService.updateProfession(id, professionVolatile);
        return ResponseEntity.ok(professionMapper.toProfessionDto(updatedProfession));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProfession(@PathVariable Long id) {
        log.info("Supprimer la profession avec l'ID : {}", id);

        iProfessionService.deleteProfession(id);
        return ResponseEntity.noContent().build();
    }

}
