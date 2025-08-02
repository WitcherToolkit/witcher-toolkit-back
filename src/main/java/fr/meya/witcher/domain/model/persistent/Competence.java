package fr.meya.witcher.domain.model.persistent;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import fr.meya.witcher.domain.model.persistent.Caracteristique;

import java.util.List;

/**
 * Classe représentant les compétences d'un personnage (connaissance de la rue, arbalète, etc...).
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Competence {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDCOMPETENCE")
	private long idCompetence;

	@Size(max = 50)
	private String nom;

	@NotBlank
	private String description;

	@Column(name = "ISEXCLUSIVE")
	private boolean isExclusive;

	@Size( max = 20)
	private String prerequis;

	@Size( max = 20)
	private String specialisation;

	@ManyToOne
	@JoinColumn(name = "IDCARACTERISTIQUE", referencedColumnName = "IDCARACTERISTIQUE")
	private Caracteristique caracteristique;

	@OneToMany(mappedBy = "competence", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CompetenceProfession> professionList;

}
