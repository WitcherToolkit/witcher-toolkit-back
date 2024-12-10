package fr.meya.witcher_toolkit_back.service;

public class PersonnageService {

	/*
	// vérifie si la profession n'a pas encore été assignée et alors initialise les compétences spécifiques.
	public void assignerProfession(Personnage personnage, Profession profession) {
		if (personnage.getProfession() == null) {
			personnage.setProfession(profession);
			initialiserCompetencesSpecifiques(personnage, profession);
		}
	}

	// Initialiser les compétences spécifiques à la profession. Elle peut être améliorée avec des règles de business pour attribuer des valeurs spécifiques.
	private void initialiserCompetencesSpecifiques(Personnage personnage, Profession profession) {
		List<CompetenceSpecifiquePersonnage> competenceSpecifiquePersonnageList = new ArrayList<>();
		if (profession.getCompetenceSpecifique() != null) {
			CompetenceSpecifiquePersonnage competenceSpecifiquePersonnage = new CompetenceSpecifiquePersonnage();
			competenceSpecifiquePersonnage.setCompetenceSpecifique(profession.getCompetenceSpecifique());
			competenceSpecifiquePersonnage.setPersonnage(personnage);
			competenceSpecifiquePersonnage.setValeurActuelle(0); // Valeur initiale ou logique de calcul
			competenceSpecifiquePersonnage.setValeurMax(10); // Valeur max ou logique de calcul

			competenceSpecifiquePersonnageList.add(competenceSpecifiquePersonnage);
		}
		personnage.setCompetenceSpecifiquePersonnageList(competenceSpecifiquePersonnageList);
	}
	 */
}
