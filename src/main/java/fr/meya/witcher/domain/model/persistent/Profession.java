package fr.meya.witcher.domain.model.persistent;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
	@Column(name = "IDPROFESSION")
	private long idProfession;

	@NotBlank
	private String nom;

	@NotBlank
	private String description;

	private int vigueur;

	@Column(name = "MAXSORT")
	private int maxSort;

	@Column(name = "MAXRITUEL")
	private int maxRituel;

	@Column(name = "MAXENVOUTEMENT")
	private int maxEnvoutement;

	@Column(name = "MAXINVOCATION")
	private int maxInvocation;

	@OneToMany(mappedBy = "profession", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<InventaireWiki> inventaireWikiList;

	@OneToMany(mappedBy = "profession", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CompetenceProfession> competenceProfessionList;

}
