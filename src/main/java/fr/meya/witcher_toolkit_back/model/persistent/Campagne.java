package fr.meya.witcher_toolkit_back.model.persistent;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	private long idCampagne;

	@NotBlank
	private String nom;

	@ManyToOne
	@JoinColumn(name = "idUser", nullable = false)
	private  User user;

}
