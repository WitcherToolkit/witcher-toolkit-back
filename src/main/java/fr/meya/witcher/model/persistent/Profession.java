package fr.meya.witcher.model.persistent;

import jakarta.persistence.*;
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
	@JoinColumn(name = "IDPROFESSION")
	private long idProfession;

	@NotBlank
	private String nom;

	@NotBlank
	@JoinColumn(name = "COMPETENCEEXCLUSIVE")
	private String competenceExclusive;

	@NotBlank
	private String description;

	@JoinColumn(name = "CODECARACTERISTIQUE")
	private  String codeCaracteristique;

	@OneToMany(mappedBy = "profession", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CompetenceSpecifique> competenceSpecifique;

}
