package fr.meya.witcher.domain.port.in;

import fr.meya.witcher.domain.model.persistent.Competence;
import fr.meya.witcher.message.response.CompetenceVolatile;

import java.util.List;

public interface ICompetenceService {

	boolean isValid(CompetenceVolatile competenceVolatile);

	List<CompetenceVolatile> getCompetenceList();

	Competence getCompetence(Long idCompetence);

	Competence createCompetence(CompetenceVolatile competenceVolatile);

	Competence updateCompetence(Long idCompetence, CompetenceVolatile competenceVolatile);

	void deleteCompetence(Long idCompetence);
}
