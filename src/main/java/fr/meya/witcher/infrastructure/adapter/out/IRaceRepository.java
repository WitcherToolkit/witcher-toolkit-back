package fr.meya.witcher.infrastructure.adapter.out;

import fr.meya.witcher.domain.model.persistent.Race;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRaceRepository extends JpaRepository<Race, Long> {
}
