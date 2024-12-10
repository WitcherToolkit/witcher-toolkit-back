package fr.meya.witcher_toolkit_back.model.persistent;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Classe décrivant un personnage
 */
@Getter
@Setter
public class Personnage {

	private int id;

	private String nomJoueur;

	private String nomPersonnage;

	private String nomImage;

	private String urlImage;

	private String terreNatale;

	private boolean isBestiaire;

	private int age;

	private String genre;

	private String pointProgression;

	// Voir pour énum
	private String race;

	private Profession profession;

	private List<CompetenceSpecifiquePersonnage> competenceSpecifiquePersonnageList;

	private List<CaracteristiquePersonnage> caracteristiquePersonnageList;

	private List<CompetenceGeneralePersonnage> competencePersonnageList;

	private List<Rituel> rituelList;

	private List<Envoutement> envoutementList;

	private List<Signe> signe;

	private List<Sort> sort;

}
