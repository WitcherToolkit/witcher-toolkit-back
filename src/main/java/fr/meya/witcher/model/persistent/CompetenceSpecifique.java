package fr.meya.witcher.model.persistent;

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
	@Column(name = "IDCOMPETENCESPECIFIQUE")
	private long idCompetenceSpecifique;

	@NotBlank
	private String nom;

	@NotBlank
	private String description;

	@NotBlank
	@Column(name = "CODECARACTERISTIQUE")
	private String codeCaracteristique;

	@NotBlank
	private String specialisation;

	@NotBlank
	private String prerequis;

	@ManyToOne
	@JoinColumn(name = "IDPROFESSION", nullable = false)
	private Profession profession;

}
