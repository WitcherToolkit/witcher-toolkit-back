package fr.meya.witcher_toolkit_back.model.persistent;

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
	@JoinTable(name = "magiePersonnage",
			joinColumns = @JoinColumn(name = "idMagie"),
			inverseJoinColumns = @JoinColumn(name = "idPersonnage"))
	private List<Personnage> personnageList;
}
