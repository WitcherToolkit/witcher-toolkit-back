package fr.meya.witcher.domain.model.persistent;

import fr.meya.witcher.domain.model.enums.NatureMagieEnum;
import fr.meya.witcher.domain.model.enums.TypeMagieEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
	@Column(name = "id_magie")
	private Long idMagie;

	@NotBlank
	@Size(max = 60)
	private String nom;

	@NotBlank
	@Size(max = 10)
	private String cout;

	@NotBlank
	private String effet;

	@Size(max = 20)
	private String portee;

	@NotBlank
	@Size(max = 35)
	private String duree;

	@NotNull
	@Enumerated(EnumType.STRING)
	private NatureMagieEnum nature;

	@NotNull
	@Enumerated(EnumType.STRING)
	private TypeMagieEnum type;

	@NotBlank
	@Size(max = 35)
	private String niveau;

	@Size(max = 25)
	private String contre;

	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}) // Pour éviter que hibernate ne charge inutilement la relation personnageList lors de l'update.
	@JoinTable(name = "magie_personnage",
			joinColumns = @JoinColumn(name = "id_magie"),
			inverseJoinColumns = @JoinColumn(name = "id_personnage"))
	private List<Personnage> personnageList;
}
