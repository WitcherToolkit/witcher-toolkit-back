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
	@Column(name = "IDCAMPAGNE")
	private long idCampagne;

	@NotBlank
	private String nom;

	@ManyToOne
	@JoinColumn(name = "IDPROFILEUTILISATEUR", nullable = false)
	private ProfilUtilisateur profilUtilisateur;

}
