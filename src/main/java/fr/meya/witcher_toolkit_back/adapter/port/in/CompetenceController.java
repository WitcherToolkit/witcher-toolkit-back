package fr.meya.witcher_toolkit_back.adapter.port.in;

import fr.meya.witcher_toolkit_back.model.persistent.Competence;
import fr.meya.witcher_toolkit_back.service.ICompetenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/competence")
public class CompetenceController {

	private final ICompetenceService competenceService;

	public CompetenceController (ICompetenceService competenceService) {
		this.competenceService = competenceService;
	}

	@GetMapping(value = "/list")
	public List<Competence> list() {
		log.info("consultation competence");
		return competenceService.getCompetenceList();
	}



}
