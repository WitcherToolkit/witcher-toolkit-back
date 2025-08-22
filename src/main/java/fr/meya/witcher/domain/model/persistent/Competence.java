package fr.meya.witcher.domain.model.persistent;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import fr.meya.witcher.domain.model.persistent.Caracteristique;

import java.util.ArrayList;
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
	@Column(name = "id_competence")
	private long idCompetence;

	@Size(max = 50)
	private String nom;

	@NotBlank
	private String description;

	@Column(name = "exclusive")
	private boolean exclusive;

	@Size( max = 20)
	private String prerequis;

	@Size( max = 20)
	private String specialisation;

	@ManyToOne
	@JoinColumn(name = "id_caracteristique", referencedColumnName = "id_caracteristique")
	private Caracteristique caracteristique;

	@OneToMany(mappedBy = "competence", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CompetenceProfession> professionList = new ArrayList<>();

}
