package fr.meya.witcher.domain.model.persistent;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

	private String nom;

	private String description;

	private String prerequis;

	@Column(name = "SPECIALISATION")
	private String specialisation;

	@Column(name = "ISEXCLUSIVE")
	private boolean isExclusive;

	@ManyToOne
	@JoinColumn(name = "IDCARACTERISTIQUE", referencedColumnName = "IDCARACTERISTIQUE")
	private Caracteristique caracteristique;

	private String tags;

	@OneToMany(mappedBy = "competence", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CompetenceProfession> professionList;

}
