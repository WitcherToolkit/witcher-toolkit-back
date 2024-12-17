package fr.meya.witcher.domain.model.persistent;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	//@JsonBackReference // Empêche la sérialisation "retour" vers profession
	private Profession profession;

}
