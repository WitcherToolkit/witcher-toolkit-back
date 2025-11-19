package fr.meya.witcher.infrastructure.adapter.out;

import fr.meya.witcher.domain.model.persistent.Profession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IProfessionRepository extends JpaRepository<Profession, UUID> {
	// Aucune méthode supplémentaire ici
}
