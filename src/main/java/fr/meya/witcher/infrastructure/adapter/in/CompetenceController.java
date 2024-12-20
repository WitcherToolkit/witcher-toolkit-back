package fr.meya.witcher.infrastructure.adapter.in;

import fr.meya.witcher.application.mapper.CompetenceMapper;
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
	private final CompetenceMapper competenceMapper;

	public CompetenceController(ICompetenceService iCompetenceService, CompetenceMapper competenceMapper) {
		this.iCompetenceService = iCompetenceService;
		this.competenceMapper = competenceMapper;
	}

	@GetMapping(value = "/list")
	public ResponseEntity<List<CompetenceVolatile>> listCompetence() {
		log.info("consultation compétence");
		List<CompetenceVolatile> result = iCompetenceService.getCompetenceList();
		return ResponseEntity.ok(result);
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
	public ResponseEntity<CompetenceVolatile> updateCompetence(@PathVariable Long id, @RequestBody CompetenceVolatile competenceVolatile) {
		log.info("modifier une compétence");
		Competence updatedCompetence = iCompetenceService.updateCompetence(id, competenceVolatile);
		return ResponseEntity.ok(competenceMapper.toCompetenceDto(updatedCompetence));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteCompetence(@PathVariable Long id) {
		log.info("Supprimer la compétence avec l'ID : {}", id);
		iCompetenceService.deleteCompetence(id);
		return ResponseEntity.noContent().build();
	}
	
}

