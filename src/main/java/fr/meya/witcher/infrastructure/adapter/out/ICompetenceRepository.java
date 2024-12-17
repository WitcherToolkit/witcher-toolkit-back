package fr.meya.witcher.infrastructure.adapter.out;

import fr.meya.witcher.domain.model.persistent.Competence;
import org.springframework.data.repository.ListCrudRepository;


public interface ICompetenceRepository extends ListCrudRepository<Competence, Long> {
}
