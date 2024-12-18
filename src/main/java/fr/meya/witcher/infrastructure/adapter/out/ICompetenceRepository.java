package fr.meya.witcher.infrastructure.adapter.out;

import fr.meya.witcher.domain.model.persistent.Competence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICompetenceRepository extends JpaRepository<Competence, Long> {
}
