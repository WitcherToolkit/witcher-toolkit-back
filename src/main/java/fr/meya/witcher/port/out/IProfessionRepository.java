package fr.meya.witcher.port.out;

import fr.meya.witcher.model.persistent.Profession;
import org.springframework.data.repository.ListCrudRepository;

public interface IProfessionRepository extends ListCrudRepository<Profession, Long> {
}
