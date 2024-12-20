package fr.meya.witcher.domain.model.persistent;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Représente les sorts, signes et invocation qu'un mage, sorceleur ou prêtre peut faire.
 */
@Getter
@Setter
@Entity
public class Magie {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name = "IDMAGIE")
	private long idMagie;

	@NotBlank
	private String nom;

	@NotBlank
	private String cout;

	@NotBlank
	private String effet;

	private String portee;

	@NotBlank
	private String duree;

	private String element;

	@NotBlank
	private String niveau;

	private String contre;

	@NotBlank
	private String profession;

	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}) // Pour éviter que hibernate ne charge inutilement la relation personnageList lors de l'update.
	@JoinTable(name = "magie_personnage",
			joinColumns = @JoinColumn(name = "IDMAGIE"),
			inverseJoinColumns = @JoinColumn(name = "IDPERSONNAGE"))
	private List<Personnage> personnageList;
}
