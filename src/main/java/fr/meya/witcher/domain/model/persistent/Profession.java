package fr.meya.witcher.domain.model.persistent;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Profession {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDPROFESSION")
	private long idProfession;

	@NotBlank
	private String nom;

	@NotBlank
	@Column(name = "COMPETENCEEXCLUSIVE")
	private String competenceExclusive;

	@NotBlank
	private String description;

	@Column(name = "CODECARACTERISTIQUE")
	private  String codeCaracteristique;

	@OneToMany(mappedBy = "profession", cascade = CascadeType.ALL, orphanRemoval = true)
	//@JsonManagedReference // Gère la sérialisation vers competenceSpecifique
	private List<CompetenceSpecifique> competenceSpecifique;

}
