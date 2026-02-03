package fr.meya.witcher.domain.model.persistent;

import fr.meya.witcher.domain.model.key.CompetencePersonnageId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe représentant les valeurs réelles et maximales de chaque compétence du personnage.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "competence_personnage")
public class CompetencePersonnage {

	@EmbeddedId
	private CompetencePersonnageId id;

	@ManyToOne
	@MapsId("idCompetence")
	@JoinColumn(name = "id_competence", nullable = false, foreignKey = @ForeignKey(name = "fk_competence_personnage_competence"))
	private Competence competence;

	@ManyToOne
	@MapsId("idPersonnage")
	@JoinColumn(name = "id_personnage", nullable = false, foreignKey = @ForeignKey(name = "fk_competence_personnage_personnage"))
	private Personnage personnage;

	@NotNull
	private int valeurActuelle;

	@NotNull
	private int valeurMax;

}
