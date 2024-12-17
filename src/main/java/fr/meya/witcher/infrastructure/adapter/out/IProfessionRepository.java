package fr.meya.witcher.infrastructure.adapter.out;

import fr.meya.witcher.domain.model.persistent.Profession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProfessionRepository extends JpaRepository<Profession, Long> {
	// Aucune méthode supplémentaire ici
}
