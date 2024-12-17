package fr.meya.witcher.infrastructure.adapter.out;

import fr.meya.witcher.domain.model.persistent.Caracteristique;
import org.springframework.data.repository.ListCrudRepository;

public interface ICaracteristiqueRepository extends ListCrudRepository<Caracteristique, Long> {

}
