package fr.meya.witcher.domain.model.persistent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.meya.witcher.domain.model.key.CompetenceProfessionId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "competence_profession")
public class CompetenceProfession {


	/**
	 * Représente la clé primaire composite de cette entité.
	 *
	 * L’annotation @EmbeddedId indique que la clé primaire est une classe embarquée
	 * (ici : CompetenceProfessionId) qui contient les deux champs id_profession et id_competence.
	 *
	 * Cette approche permet à JPA de gérer automatiquement les jointures sur ces deux champs,
	 * tout en offrant une structure claire pour les relations "many-to-many avec attributs".
	 */
	@EmbeddedId
	private CompetenceProfessionId id;

	/**
	 * Relation vers l'entité Profession.
	 *
	 * L’annotation @JoinColumn précise la colonne dans la table actuelle ("id_profession")
	 * utilisée pour faire la jointure avec la table Profession.
	 *
	 * Cette relation est une partie de la clé composite, donc elle est couplée à
	 * @MapsId("idProfession") pour synchroniser avec le champ de la clé composite.
	 */
	@ManyToOne
	@JsonIgnore
	@MapsId("idProfession")
	@JoinColumn(name = "id_profession", nullable = false, foreignKey = @ForeignKey(name = "fk_competence_profession_profession"))
	private Profession profession;

	/**
	 * Relation vers l'entité Competence.
	 *
	 * Même logique que ci-dessus, mais pour la compétence. Cela permet de relier chaque enregistrement
	 * de la table d'association à une compétence spécifique.
	 */
	@ManyToOne
	@MapsId("idCompetence")
	@JoinColumn(name = "id_competence", nullable = false, foreignKey = @ForeignKey(name = "fk_competence_profession_competence"))
	private Competence competence;

}
