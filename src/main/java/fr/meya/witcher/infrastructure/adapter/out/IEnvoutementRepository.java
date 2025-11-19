package fr.meya.witcher.infrastructure.adapter.out;

import fr.meya.witcher.domain.model.persistent.Envoutement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IEnvoutementRepository extends JpaRepository<Envoutement, UUID> {
}
