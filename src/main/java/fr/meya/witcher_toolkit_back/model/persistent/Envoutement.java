package fr.meya.witcher_toolkit_back.model.persistent;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Envoutement {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private long idEnvoutement;

	@NotBlank
	private String nom;

	@NotBlank
	private String cout;

	@NotBlank
	private String effet;

	@NotBlank
	private String prerequis;

	@NotBlank
	private String danger;

	@ManyToMany
	@JoinTable(
			name = "envoutementPersonnage", // Nom de la table de jointure
			joinColumns = @JoinColumn(name = "idEnvoutement"), // Colonne représentant l'entité Envoutement
			inverseJoinColumns = @JoinColumn(name = "idPersonnage") // Colonne représentant l'entité Personnage
	)
	private List<Personnage> personnageList;

}
