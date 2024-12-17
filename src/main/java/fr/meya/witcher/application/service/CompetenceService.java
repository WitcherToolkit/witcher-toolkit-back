package fr.meya.witcher.application.service;

import fr.meya.witcher.domain.model.persistent.Competence;
import fr.meya.witcher.domain.port.in.ICompetenceService;
import fr.meya.witcher.infrastructure.adapter.out.ICompetenceRepository;
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
