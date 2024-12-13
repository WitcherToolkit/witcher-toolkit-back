package fr.meya.witcher_toolkit_back.service;

import fr.meya.witcher_toolkit_back.model.persistent.Competence;
import fr.meya.witcher_toolkit_back.port.out.ICompetenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetenceService implements ICompetenceService {

	private final ICompetenceRepository competenceRepository;

	public CompetenceService(ICompetenceRepository competenceRepository) {
		this.competenceRepository = competenceRepository;
	}

	@Override
	public List<Competence> getCompetenceList() {
		return competenceRepository.findAll();
	}
}
