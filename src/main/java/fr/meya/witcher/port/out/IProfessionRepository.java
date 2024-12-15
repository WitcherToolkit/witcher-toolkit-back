package fr.meya.witcher.port.out;

import fr.meya.witcher.model.persistent.Profession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProfessionRepository extends JpaRepository<Profession, Long>, IProfessionRepositoryCustom {
}
