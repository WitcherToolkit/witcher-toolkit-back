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

	@OneToMany(mappedBy = "profession", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CompetenceProfession> competenceProfession;

}
