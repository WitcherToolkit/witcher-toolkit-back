package fr.meya.witcher_toolkit_back.model.persistent;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Campagne géré par les utilisateurs.
 */
@Getter
@Setter
@Entity
public class Campagne {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private int idCampagne;

	@NotBlank
	private String nom;

	@ManyToOne
	@JoinColumn(name = "idUser", nullable = false)
	private  User user;

}
