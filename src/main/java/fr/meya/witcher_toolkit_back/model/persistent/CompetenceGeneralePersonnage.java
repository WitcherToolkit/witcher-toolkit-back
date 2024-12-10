package fr.meya.witcher_toolkit_back.model.persistent;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe représentant les valeurs réelles et maximales de chaque compétence du personnage.
 */
@Getter
@Setter
@Entity
public class CompetenceGeneralePersonnage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private CompetenceGenerale competenceGenerale;

	private Personnage personnage;

	private  int valeur;

}
