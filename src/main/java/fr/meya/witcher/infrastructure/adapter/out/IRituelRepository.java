package fr.meya.witcher.infrastructure.adapter.out;

import fr.meya.witcher.domain.model.persistent.Rituel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IRituelRepository extends JpaRepository<Rituel, UUID> {
}
