package fr.meya.witcher_toolkit_back.model.persistent;

import lombok.Getter;
import lombok.Setter;

/**
 * Classe représentant les valeurs réelles et maximales de chaque caractéristique du personnage.
 */
@Getter
@Setter
public class CaracteristiquePersonnage {

	private int id;

	private Personnage idPersonnage;

	private Caracteristique idCaracteristique;

	private int valeurMax;

	private int valeurActuelle;

}
