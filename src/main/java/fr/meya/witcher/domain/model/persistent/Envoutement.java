package fr.meya.witcher.domain.model.persistent;

import fr.meya.witcher.domain.model.enums.DangerEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Envoutement {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name = "IDENVOUTEMENT")
	private long idEnvoutement;

	@NotBlank
	@Size(max = 60)
	private String nom;

	@NotBlank
	@Size(max = 10)
	private String cout;

	@NotBlank
	private String effet;

	@NotBlank
	private String prerequis;

	@NotNull
	@Enumerated(EnumType.STRING)
	private DangerEnum danger;

	@ManyToMany
	@JoinTable(
			name = "envoutementPersonnage", // Nom de la table de jointure
			joinColumns = @JoinColumn(name = "IDENVOUTEMENT"), // Colonne représentant l'entité Envoutement
			inverseJoinColumns = @JoinColumn(name = "IDPERSONNAGE") // Colonne représentant l'entité Personnage
	)
	private List<Personnage> personnageList;

}
