package fr.meya.witcher.infrastructure.adapter.in;

import fr.meya.witcher.domain.model.persistent.Competence;
import fr.meya.witcher.domain.port.in.ICompetenceService;
import fr.meya.witcher.message.response.CompetenceVolatile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/competence")
public class CompetenceController {

	private final ICompetenceService iCompetenceService;

	public CompetenceController(ICompetenceService iCompetenceService) {
		this.iCompetenceService = iCompetenceService;
	}

	@GetMapping(value = "/list")
	public List<Competence> listCompetence() {
		log.info("consultation compétence");
		return iCompetenceService.getCompetenceList();
	}

	@GetMapping(value = "/list/{id}")
	public Competence getCompetence(@PathVariable Long id) {
		log.info("consultation compétence");
		return iCompetenceService.getCompetence(id);
	}

	@PostMapping("/create")
	public ResponseEntity<Competence> createCompetence(@RequestBody CompetenceVolatile competenceVolatile) {
		log.info("Créer une compétence");

		// Appel direct au service avec l'objet reçu
		Competence createdCompetence = iCompetenceService.createCompetence(competenceVolatile);

		// Retourner la réponse
		return ResponseEntity.ok(createdCompetence);
	}

	@PutMapping(value = "/update/{id}")
	public ResponseEntity<Competence> updateCompetence(@PathVariable Long id, @RequestBody CompetenceVolatile competenceVolatile) {
		log.info("modifier une compétence");
		Competence updatedCompetence = iCompetenceService.updateCompetence(id, competenceVolatile);
		return ResponseEntity.ok(updatedCompetence);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Competence> deleteCompetence(@PathVariable Long id) {
		log.info("Supprimer la compétence avec l'ID : {}", id);
		Competence deletedCompetence = iCompetenceService.deleteCompetence(id);
		// Retourne la compétence supprimée dans la réponse
		return ResponseEntity.ok(deletedCompetence);
	}
	
}

