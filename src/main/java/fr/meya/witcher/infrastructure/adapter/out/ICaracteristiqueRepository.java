package fr.meya.witcher.infrastructure.adapter.out;

import fr.meya.witcher.domain.model.persistent.Caracteristique;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICaracteristiqueRepository extends JpaRepository<Caracteristique, Long> {

}
