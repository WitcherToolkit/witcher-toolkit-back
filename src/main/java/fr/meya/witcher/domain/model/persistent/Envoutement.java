package fr.meya.witcher.domain.model.persistent;

import jakarta.persistence.*;
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
	@Column(name = "IDENVOUTEMENT")
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
			joinColumns = @JoinColumn(name = "IDENVOUTEMENT"), // Colonne représentant l'entité Envoutement
			inverseJoinColumns = @JoinColumn(name = "IDPERSONNAGE") // Colonne représentant l'entité Personnage
	)
	private List<Personnage> personnageList;

}
