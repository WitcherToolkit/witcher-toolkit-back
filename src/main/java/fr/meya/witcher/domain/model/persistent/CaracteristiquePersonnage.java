package fr.meya.witcher.domain.model.persistent;

import fr.meya.witcher.domain.model.key.CaracteristiquePersonnageId;
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
	private CaracteristiquePersonnageId id;

	private int valeurMax;
	private int valeurActuelle;

	@ManyToOne
	@MapsId("idPersonnage") // correspond au champ dans l'EmbeddedId
	@JoinColumn(name = "id_personnage", nullable = false)
	private Personnage personnage;

	@ManyToOne
	@MapsId("idCaracteristique") // correspond au champ dans l'EmbeddedId
	@JoinColumn(name = "id_caracteristique", nullable = false)
	private Caracteristique caracteristique;

}
