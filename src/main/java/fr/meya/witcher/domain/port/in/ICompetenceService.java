package fr.meya.witcher.domain.port.in;

import fr.meya.witcher.domain.model.persistent.Competence;

import java.util.List;

public interface ICompetenceService {

	List<Competence> getCompetenceList();

}
