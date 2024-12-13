package fr.meya.witcher.model.persistent;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Rituel {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column(name = "IDRITUEL")
	private long idRituel;

	@NotBlank
	private String nom;

	@NotBlank
	private String cout;

	@NotBlank
	private String effet;

	@NotBlank
	private String tempsPreparation;

	@NotBlank
	private String sd;

	@NotBlank
	private String duree;

	@NotBlank
	private String composant;

	@NotBlank
	private String niveau;

	@ManyToMany
	@JoinTable(
			name = "personnageRituel",
			joinColumns = @JoinColumn(name = "IDRITUEL"),
			inverseJoinColumns = @JoinColumn(name = "IDPERSONNAGE"))
	private List<Personnage> personnageList;

}
