package fr.meya.witcher.domain.port.in;

import fr.meya.witcher.domain.model.persistent.Competence;
import fr.meya.witcher.message.response.CompetenceVolatile;

import java.util.List;
import java.util.UUID;

public interface ICompetenceService {

	boolean isValid(CompetenceVolatile competenceVolatile);

	List<CompetenceVolatile> getCompetenceList();

	Competence getCompetence(UUID idCompetence);

	Competence createCompetence(CompetenceVolatile competenceVolatile);

	Competence updateCompetence(UUID idCompetence, CompetenceVolatile competenceVolatile);

	void deleteCompetence(UUID idCompetence);
}
