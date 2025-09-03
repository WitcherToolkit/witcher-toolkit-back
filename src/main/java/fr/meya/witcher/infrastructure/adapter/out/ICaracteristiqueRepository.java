package fr.meya.witcher.infrastructure.adapter.out;

import fr.meya.witcher.domain.model.persistent.Caracteristique;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICaracteristiqueRepository extends JpaRepository<Caracteristique, Long> {
    List<Caracteristique> findAllByOrderByNomAsc();
}
