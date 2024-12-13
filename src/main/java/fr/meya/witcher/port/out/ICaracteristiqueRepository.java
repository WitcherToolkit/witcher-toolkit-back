package fr.meya.witcher.port.out;

import fr.meya.witcher.model.persistent.Caracteristique;
import org.springframework.data.repository.ListCrudRepository;

public interface ICaracteristiqueRepository extends ListCrudRepository<Caracteristique, Long> {

}
