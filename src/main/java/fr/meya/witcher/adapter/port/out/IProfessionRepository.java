package fr.meya.witcher.adapter.port.out;

import fr.meya.witcher.model.persistent.Profession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProfessionRepository extends JpaRepository<Profession, Long>, IProfessionRepositoryCustom {
}
