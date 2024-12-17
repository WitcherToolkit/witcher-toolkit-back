package fr.meya.witcher.domain.port.in;

import fr.meya.witcher.domain.model.persistent.Competence;
import fr.meya.witcher.message.response.CompetenceVolatile;

import java.util.List;

public interface ICompetenceService {

	boolean isValid(CompetenceVolatile competenceVolatile);

	Competence createCompetence(CompetenceVolatile competenceVolatile);

	Competence getCompetence(Long idCompetence);

	Competence deleteCompetence(Long idCompetence);

	Competence updateCompetence(Long id, CompetenceVolatile competenceVolatile);

	List<Competence> getCompetenceList();
}
