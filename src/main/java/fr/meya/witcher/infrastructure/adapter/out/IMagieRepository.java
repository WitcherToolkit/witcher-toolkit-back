package fr.meya.witcher.infrastructure.adapter.out;

import fr.meya.witcher.domain.model.persistent.Magie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IMagieRepository extends JpaRepository<Magie, UUID> {
    List<Magie> findByNiveauIgnoreCase(String niveau);
}
