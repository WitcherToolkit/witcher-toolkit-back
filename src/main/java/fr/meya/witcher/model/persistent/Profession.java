package fr.meya.witcher.model.persistent;

import jakarta.persistence.*;
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

	private String nom;

	private String competenceExclusive;

	@OneToMany(mappedBy = "profession", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CompetenceSpecifique> competenceSpecifique;


}
