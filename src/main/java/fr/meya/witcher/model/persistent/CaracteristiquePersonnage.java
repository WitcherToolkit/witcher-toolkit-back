package fr.meya.witcher.model.persistent;

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
	@Column(name = "IDCARACTERISTIQUEPERSONNAGE")
	private long idCaracteristiquePersonnage;

	private int valeurMax;

	private int valeurActuelle;

	@ManyToOne
	@JoinColumn(name = "IDPERSONNAGE", nullable = false)
	private Personnage personnage;

	@ManyToOne
	@JoinColumn(name = "IDCARACTERISTIQUE", nullable = false)
	private Caracteristique idCaracteristique;

}
