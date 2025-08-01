package fr.meya.witcher.domain.model.persistent;

import fr.meya.witcher.domain.model.enums.NatureMagieEnum;
import fr.meya.witcher.domain.model.enums.TypeMagieEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Représente les sorts, signes et invocation qu'un mage, sorceleur ou prêtre peut faire.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

	private String effet;

	@NotBlank
	private String portee;

	@NotBlank
	private String duree;

	@NotNull
	@Enumerated(EnumType.STRING)
	private NatureMagieEnum nature;

	@NotNull
	@Enumerated(EnumType.STRING)
	private TypeMagieEnum type;

	@NotBlank
	private String niveau;

	private String contre;

	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}) // Pour éviter que hibernate ne charge inutilement la relation personnageList lors de l'update.
	@JoinTable(name = "magie_personnage",
			joinColumns = @JoinColumn(name = "IDMAGIE"),
			inverseJoinColumns = @JoinColumn(name = "IDPERSONNAGE"))
	private List<Personnage> personnageList;
}
