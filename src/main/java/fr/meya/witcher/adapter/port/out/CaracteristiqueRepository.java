package fr.meya.witcher.adapter.port.out;

<<<<<<< HEAD:src/main/java/fr/meya/witcher/adapter/port/out/CaracteristiqueRepository.java
import fr.meya.witcher.model.persistent.Caracteristique;
import fr.meya.witcher.port.out.ICaracteristiqueRepository;
=======
import fr.meya.witcher_toolkit_back.model.persistent.Caracteristique;
>>>>>>> 0769ef2662738535d87b40a1d5bd0e434b6141bf:src/main/java/fr/meya/witcher_toolkit_back/adapter/port/out/CaracteristiqueRepository.java
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class CaracteristiqueRepository {

	private static List<Caracteristique> caracteristiqueList = new ArrayList<>();

	public void create(Caracteristique caracteristique) {
		caracteristiqueList.add(caracteristique);
		log.info(caracteristique.toString());
	}

	public List<Caracteristique> list() {
		//Mock des données
		Caracteristique caracteristique = new Caracteristique();
		caracteristique.setIdCaracteristique(1);
		caracteristique.setNom("Intélligence");
		caracteristique.setCode("INT");
		caracteristique.setDescription("Permet de résoudre des énigmes, réaliser des expériences scientifiques, construire des raisonnements logiques...");

		Caracteristique caracteristique1 = new Caracteristique();
		caracteristique1.setIdCaracteristique(2);
		caracteristique1.setNom("Réflexe");
		caracteristique1.setCode("REF");
		caracteristique1.setDescription("Sert à combattre, esquiver et effectuer des actions nécessitant dse réations rapides et des gestes précis.");

		// Transformation de la liste immutable en liste modifiable.
		//return new ArrayList<>(List.of(caracteristique, caracteristique1));
		return List.of(caracteristique, caracteristique1);
	}

<<<<<<< HEAD:src/main/java/fr/meya/witcher/adapter/port/out/CaracteristiqueRepository.java
	@Override
	public Caracteristique getById(int id) {
		// Mock des données
		Caracteristique caracteristique = new Caracteristique();
		caracteristique.setIdCaracteristique(1);
		caracteristique.setNom("Intélligence");
		caracteristique.setCode("INT");
		caracteristique.setDescription("Permet de résoudre des énigmes, réaliser des expériences scientifiques, construire des raisonnements logiques...");

		return caracteristique;
	}
=======
>>>>>>> 0769ef2662738535d87b40a1d5bd0e434b6141bf:src/main/java/fr/meya/witcher_toolkit_back/adapter/port/out/CaracteristiqueRepository.java
}
