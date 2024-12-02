package fr.meya.witcher_toolkit_back.model;

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

	// Voir pour enum
	private String profession;

	private List<CaracteristiquePersonnage> caracteristiquePersonnageList;

	private List<CompetencePersonnage> competencePersonnageList;

	private List<Rituel> rituelList;

	private List<Envoutement> envoutementList;

	private List<Signe> signe;

	private List<Sort> sort;

}
