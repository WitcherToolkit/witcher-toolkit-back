package fr.meya.witcher.adapter.port.out;

import fr.meya.witcher.model.persistent.Competence;
import org.springframework.data.repository.ListCrudRepository;


public interface ICompetenceRepository extends ListCrudRepository<Competence, Long> {
}
