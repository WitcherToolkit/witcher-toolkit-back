package fr.meya.witcher.infrastructure.adapter.out;

import fr.meya.witcher.domain.model.persistent.Magie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMagieRepository extends JpaRepository<Magie, Long> {
}
