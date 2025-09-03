package fr.meya.witcher.infrastructure.adapter.out;

import fr.meya.witcher.domain.model.persistent.Competence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICompetenceRepository extends JpaRepository<Competence, Long> {
    List<Competence> findAllByOrderByNomAsc();
}
