package fr.meya.witcher_toolkit_back.model.persistent;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CompetenceSpecifique {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCompetenceSpecifique;

	@NotBlank
	private String nom;

	@NotBlank
	private String description;

	@NotBlank
	private String codeCaracteristique;

	@NotBlank
	private String specialisation;

	@NotBlank
	private String presrequis;

	@ManyToOne
	private Profession profession;

}
