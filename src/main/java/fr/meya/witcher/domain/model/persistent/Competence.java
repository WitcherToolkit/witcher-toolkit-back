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

	@Column(name = "CODECARACTERISTIQUE")
	private String codeCaracteristique;

	private String description;

	@Column(name = "DESCRIPTIONBASE10")
	private String descriptionBase10;

	@Column(name = "DESCRIPTIONBASE13")
	private String descriptionBase13;

	@Column(name = "DESCRIPTIONBASE16")
	private String descriptionBase16;

	@Column(name = "DESCRIPTIONBASE20")
	private String descriptionBase20;

	@Column(name = "SPECIALISATION")
	private String specialisation;

	@Column(name = "ISEXCLUSIVE")
	private boolean isExclusive;

    @ManyToOne
    @JoinColumn(name = "IDCARACTERISTIQUE", referencedColumnName = "IDCARACTERISTIQUE")
    private Caracteristique caracteristique;

	@OneToMany(mappedBy = "competence", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CompetenceProfession> professionList;

}
