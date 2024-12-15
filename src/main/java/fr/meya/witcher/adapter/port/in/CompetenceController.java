package fr.meya.witcher.adapter.port.in;

import fr.meya.witcher.model.persistent.Competence;
import fr.meya.witcher.service.ICompetenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
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

}

