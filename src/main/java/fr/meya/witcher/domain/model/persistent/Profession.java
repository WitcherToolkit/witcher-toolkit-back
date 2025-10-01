package fr.meya.witcher.domain.model.persistent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
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
	private Long idProfession;

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

	//----------------------------------------------------------------------------------------------------------------//
    @OneToMany(mappedBy = "profession")
    @JsonIgnore
    private List<Personnage> personnages;

    @OneToMany(mappedBy = "profession", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<InventaireWiki> inventaireWikiList = new ArrayList<>();

	@OneToMany(mappedBy = "profession", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<CompetenceProfession> competenceProfessionList = new ArrayList<>();
	// new ArrayList<>() est optionnel mais limite les risques de NPE lors d'ajout ou de supression d'élement

}
