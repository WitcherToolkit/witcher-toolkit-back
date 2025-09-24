package fr.meya.witcher.infrastructure.adapter.in;

import fr.meya.witcher.application.mapper.PersonnageMapper;
import fr.meya.witcher.domain.model.persistent.Personnage;
import fr.meya.witcher.domain.port.in.IPersonnageService;
import fr.meya.witcher.message.response.CompetencePersonnageVolatile;
import fr.meya.witcher.message.response.PersonnageVolatile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/personnages")
public class PersonnageController {
    private final IPersonnageService iPersonnageService;
    private final PersonnageMapper personnageMapper;

    public PersonnageController(IPersonnageService iPersonnageService, PersonnageMapper personnageMapper) {
        this.iPersonnageService = iPersonnageService;
        this.personnageMapper = personnageMapper;
    }

    // Liste des personnages
    /*@GetMapping("/list")
    public ResponseEntity<List<CompetencePersonnageVolatile>> listPersonnages() {
        log.info("Consultation des personnages");
        List<PersonnageVolatile> result = iPersonnageService.getPersonnageList();
        return ResponseEntity.ok(result);
    }*/

    // Consulter un personnage par son ID
    @GetMapping("/{id}")
    public ResponseEntity<PersonnageVolatile> getPersonnage(@PathVariable Long id) {
        log.info("Consultation du personnage - ID : {}", id);
        Personnage personnage = iPersonnageService.getPersonnage(id);
        return ResponseEntity.ok(personnageMapper.toPersonnageDto(personnage));
    }

    // Créer un nouveau personnage
    @PostMapping("/create")
    public ResponseEntity<Personnage> createPersonnage(@RequestBody PersonnageVolatile personnageVolatile) {
        log.info("Ajout d'un personnage");
        Personnage createdPersonnage = iPersonnageService.createPersonnage(personnageVolatile);
        return ResponseEntity.ok(createdPersonnage);
    }

    // Mettre à jour un personnage
    @PutMapping("/update/{id}")
    public ResponseEntity<PersonnageVolatile> updatePersonnage(@PathVariable Long id, @RequestBody PersonnageVolatile personnageVolatile) {
        log.info("Modification du personnage - ID : {} - Données : {}", id, personnageVolatile);
        Personnage updatedPersonnage = iPersonnageService.updatePersonnage(id, personnageVolatile);
        return ResponseEntity.ok(personnageMapper.toPersonnageDto(updatedPersonnage));
    }

    // Supprimer un personnage
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePersonnage(@PathVariable Long id) {
        log.info("Suppression du personnage avec l'ID : {}", id);
        iPersonnageService.deletePersonnage(id);
        return ResponseEntity.noContent().build();
    }
}