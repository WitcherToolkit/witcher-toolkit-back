package fr.meya.witcher.model.persistent;

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
	private String effet;

	@NotBlank
	private String portee;

	@NotBlank
	private String duree;

	@NotBlank
	private String element;

	@NotBlank
	private String niveau;

	@NotBlank
	private String contre;

	@NotBlank
	private String profession;

	@ManyToMany
	@JoinTable(name = "MAGIEPERSONNAGE",
			joinColumns = @JoinColumn(name = "IDMAGIE"),
			inverseJoinColumns = @JoinColumn(name = "IDPERSONNAGE"))
	private List<Personnage> personnageList;
}
