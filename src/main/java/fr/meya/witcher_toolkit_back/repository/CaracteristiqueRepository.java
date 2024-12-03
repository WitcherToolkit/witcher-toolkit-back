package fr.meya.witcher_toolkit_back.repository;

import fr.meya.witcher_toolkit_back.model.Caracteristique;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CaracteristiqueRepository {
	public List<Caracteristique> list() {
		//Mock des données
		Caracteristique caracteristique = new Caracteristique();
		caracteristique.setId(1);
		caracteristique.setNom("nom1");
		caracteristique.setCompetence("competence1");
		caracteristique.setDescription("description1");

		Caracteristique caracteristique1 = new Caracteristique();
		caracteristique1.setId(1);
		caracteristique1.setNom("nom2");
		caracteristique1.setCompetence("competence2");
		caracteristique1.setDescription("description2");

		return List.of(caracteristique, caracteristique1, caracteristique);
	}
}
