package fr.meya.witcher.infrastructure.adapter.out;

import fr.meya.witcher.domain.model.persistent.Envoutement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEnvoutementRepository extends JpaRepository<Envoutement, Long> {
}
