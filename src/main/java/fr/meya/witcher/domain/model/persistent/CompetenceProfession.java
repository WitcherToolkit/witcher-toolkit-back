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
public class CompetenceProfession {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDCOMPETENCEPROFESSION")
	private long idCompetenceProfession;

	@ManyToOne
	@JoinColumn(name = "IDPROFESSION", nullable = false)
	private Profession profession;

	@ManyToOne
	@JoinColumn(name = "IDCOMPETENCE", nullable = false)
	private Competence competence;

}
