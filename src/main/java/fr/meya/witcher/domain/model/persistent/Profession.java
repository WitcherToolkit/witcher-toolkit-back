package fr.meya.witcher.domain.model.persistent;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Profession {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_profession")
	private long idProfession;

	@NotBlank
	@Size(max = 50)
	private String nom;

	@NotBlank
	private String description;

	private int vigueur;

	@Column(name = "nb_objet")
	private int nbObjet;

	@Column(name = "max_sort")
	private int maxSort;

	@Column(name = "max_rituel")
	private int maxRituel;

	@Column(name = "max_envoutement")
	private int maxEnvoutement;

	@Column(name = "max_invocation")
	private int maxInvocation;

	@OneToMany(mappedBy = "profession", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<InventaireWiki> inventaireWikiList;

	@OneToMany(mappedBy = "profession", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CompetenceProfession> competenceProfessionList;

}
