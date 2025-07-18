package fr.meya.witcher.domain.model.persistent;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "COMPETENCEPROFESSION")
public class CompetenceProfession {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDCOMPTETENCEPROFESSION")
	private long idCompetenceProfession;

	@ManyToOne
	@JoinColumn(name = "IDPROFESSION", nullable = false)
	private Profession profession;

	@ManyToOne
	@JoinColumn(name = "IDCOMPETENCE", nullable = false)
	private Competence competence;

}
