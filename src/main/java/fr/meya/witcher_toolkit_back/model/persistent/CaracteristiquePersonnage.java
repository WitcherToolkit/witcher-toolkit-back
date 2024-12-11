package fr.meya.witcher_toolkit_back.model.persistent;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe représentant les valeurs réelles et maximales de chaque caractéristique du personnage.
 */
@Getter
@Setter
@Entity
public class CaracteristiquePersonnage {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private int idCaracteristiquePersonnage;

	private int valeurMax;

	private int valeurActuelle;

	@ManyToOne
	@JoinColumn(name = "idPersonnage", nullable = false)
	private Personnage idPersonnage;

	@ManyToOne
	@JoinColumn(name = "idCaracteristique", nullable = false)
	private Caracteristique idCaracteristique;

}
