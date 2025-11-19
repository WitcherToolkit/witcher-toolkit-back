package fr.meya.witcher.infrastructure.adapter.out;

import fr.meya.witcher.domain.model.persistent.Personnage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IPersonnageRepository  extends JpaRepository<Personnage, UUID> {
}
