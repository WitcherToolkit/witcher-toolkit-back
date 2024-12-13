package fr.meya.witcher.port.out;

import fr.meya.witcher.model.persistent.Competence;
import org.springframework.data.repository.ListCrudRepository;


public interface ICompetenceRepository extends ListCrudRepository<Competence, Long> {
}
