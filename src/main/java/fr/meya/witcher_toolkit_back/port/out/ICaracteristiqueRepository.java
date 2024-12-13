package fr.meya.witcher_toolkit_back.port.out;

import fr.meya.witcher_toolkit_back.model.persistent.Caracteristique;
import org.springframework.data.repository.ListCrudRepository;

public interface ICaracteristiqueRepository extends ListCrudRepository<Caracteristique, Long> {

}
