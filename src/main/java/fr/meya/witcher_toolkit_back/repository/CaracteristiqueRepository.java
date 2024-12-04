package fr.meya.witcher_toolkit_back.repository;

import fr.meya.witcher_toolkit_back.model.Caracteristique;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CaracteristiqueRepository {
	public List<Caracteristique> list() {
		//Mock des données
		Caracteristique caracteristique = new Caracteristique();
		caracteristique.setId(1);
		caracteristique.setNom("Intélligence");
		caracteristique.setCode("INT");
		caracteristique.setDescription("Permet de résoudre des énigmes, réaliser des expériences scientifiques, construire des raisonnements logiques...");

		Caracteristique caracteristique1 = new Caracteristique();
		caracteristique1.setId(2);
		caracteristique1.setNom("Réflexe");
		caracteristique1.setCode("REF");
		caracteristique1.setDescription("Sert à combattre, esquiver et effectuer des actions nécessitant dse réations rapides et des gestes précis.");

		// Transformation de la liste immutable en liste modifiable.
		return new ArrayList<>(List.of(caracteristique, caracteristique1));
	}
}
