package fr.meya.witcher.infrastructure.adapter.out;

import fr.meya.witcher.domain.model.persistent.InventaireWiki;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IInventaireWikiRepository  extends JpaRepository<InventaireWiki, Long> {
}
