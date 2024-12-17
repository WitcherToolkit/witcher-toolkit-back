package fr.meya.witcher.domain.model.persistent;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CompetenceSpecifiquePersonnage {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column(name = "IDCOMPETENCESPECIFIQUEPERSONNAGE")
	private long idCompetenceSpecifiquePersonnage;

	private int valeurActuelle;

	private int valeurMax;

	@ManyToOne
	@JoinColumn(name = "IDPROFESSION", nullable = false)
	private Profession profession;

	@ManyToOne
	@JoinColumn(name = "IDCOMPETENCESPECIFIQUE", nullable = false)
	private CompetenceSpecifique competenceSpecifique;

	@ManyToOne
	@JoinColumn(name = "IDPERSONNAGE", nullable = false)
	private Personnage personnage;

}
