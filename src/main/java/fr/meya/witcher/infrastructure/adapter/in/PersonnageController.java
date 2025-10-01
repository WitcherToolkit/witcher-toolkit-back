package fr.meya.witcher.infrastructure.adapter.in;

import fr.meya.witcher.application.mapper.PersonnageMapper;
import fr.meya.witcher.domain.model.persistent.Personnage;
import fr.meya.witcher.domain.port.in.IPersonnageService;
import fr.meya.witcher.message.response.CompetencePersonnageVolatile;
import fr.meya.witcher.message.response.PersonnageVolatile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/personnages")
public class PersonnageController {
    private final IPersonnageService iPersonnageService;

    public PersonnageController(IPersonnageService iPersonnageService) {
        this.iPersonnageService = iPersonnageService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonnageVolatile> getPersonnage(@PathVariable Long id) {
        log.info("Consultation du personnage - ID : {}", id);
        PersonnageVolatile personnage = iPersonnageService.getPersonnage(id);
        return ResponseEntity.ok(personnage);
    }

    @PostMapping
    public ResponseEntity<PersonnageVolatile> createPersonnage(@RequestBody PersonnageVolatile personnageVolatile) {
        log.info("Création d'un personnage");
        PersonnageVolatile createdPersonnage = iPersonnageService.createPersonnage(personnageVolatile);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdPersonnage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonnageVolatile> updatePersonnage(@PathVariable Long id, @RequestBody PersonnageVolatile personnageVolatile) {
        log.info("Modification du personnage - ID : {}", id);
        PersonnageVolatile updatedPersonnage = iPersonnageService.updatePersonnage(id, personnageVolatile);
        return ResponseEntity.ok(updatedPersonnage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonnage(@PathVariable Long id) {
        log.info("Suppression du personnage - ID : {}", id);
        iPersonnageService.deletePersonnage(id);
        return ResponseEntity.noContent().build();
    }
}