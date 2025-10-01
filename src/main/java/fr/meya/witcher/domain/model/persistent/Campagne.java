package fr.meya.witcher.domain.model.persistent;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * Campagne géré par les utilisateurs.
 */
@Getter
@Setter
@Entity
public class Campagne {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name = "id_campagne")
	private Long idCampagne;

	@NotBlank
	private String nom;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_user", nullable = false, foreignKey = @ForeignKey(name = "fk_campagne_user"))
	private User user;

}
