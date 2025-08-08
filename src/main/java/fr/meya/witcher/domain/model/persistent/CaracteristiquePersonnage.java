package fr.meya.witcher.domain.model.persistent;

import fr.meya.witcher.domain.model.key.CompetenceProfessionId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe représentant les valeurs réelles et maximales de chaque caractéristique du personnage.
 */
@Getter
@Setter
@Entity
@Table(name = "caracteristique_personnage")
public class CaracteristiquePersonnage {

	@EmbeddedId
	private CompetenceProfessionId id;

	private int valeurMax;

	private int valeurActuelle;

	@ManyToOne
	@MapsId("idPersonnage")
	@JoinColumn(name = "id_personnage", nullable = false)
	private Personnage idPersonnage;

	@ManyToOne
	@MapsId("idCaracteristique")
	@JoinColumn(name = "id_caracteristique", nullable = false)
	private Caracteristique idCaracteristique;

}
