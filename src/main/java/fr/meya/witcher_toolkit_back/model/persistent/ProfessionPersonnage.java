package fr.meya.witcher_toolkit_back.model.persistent;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfessionPersonnage {

	private int id;

	private  Profession profession;

	private Personnage personnage;

	private  int valeurMax;

	private int valeurActuelle;
}
