package fr.meya.witcher.model.persistent;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
			joinColumns = @JoinColumn(name = "idRituel"),
			inverseJoinColumns = @JoinColumn(name = "idPersonnage"))
	private List<Personnage> personnageList;

}
