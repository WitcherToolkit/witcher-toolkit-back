package fr.meya.witcher.infrastructure.adapter.out;

import fr.meya.witcher.domain.model.persistent.Personnage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonnageRepository  extends JpaRepository<Personnage, Long> {
}
