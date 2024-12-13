package fr.meya.witcher_toolkit_back.port.out;

import fr.meya.witcher_toolkit_back.model.persistent.Competence;
import org.springframework.data.repository.ListCrudRepository;


public interface ICompetenceRepository extends ListCrudRepository<Competence, Long> {
}
