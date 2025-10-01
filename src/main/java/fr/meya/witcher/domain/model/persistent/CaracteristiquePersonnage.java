package fr.meya.witcher.domain.model.persistent;

import fr.meya.witcher.domain.model.key.CaracteristiquePersonnageId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe représentant les valeurs réelles et maximales de chaque caractéristique du personnage.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "caracteristique_personnage")
public class CaracteristiquePersonnage {

	@EmbeddedId
	private CaracteristiquePersonnageId id;

	private int valeurMax;

	private int valeurActuelle;

	@ManyToOne
	@MapsId("idPersonnage") // correspond au champ dans l'EmbeddedId
	@JoinColumn(name = "id_personnage", nullable = false, foreignKey = @ForeignKey(name = "fk_caracteristique_personnage_personnage"))
	private Personnage personnage;

	@ManyToOne
	@MapsId("idCaracteristique") // correspond au champ dans l'EmbeddedId
	@JoinColumn(name = "id_caracteristique", nullable = false, foreignKey = @ForeignKey(name = "fk_caracteristique_personnage_caracteristique"))
	private Caracteristique caracteristique;

}
