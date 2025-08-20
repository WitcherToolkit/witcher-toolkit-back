package fr.meya.witcher.domain.model.persistent;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Rituel {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column(name = "id_rituel")
	private long idRituel;

	@NotBlank
	private String nom;

	@NotBlank
	private String cout;

	@NotBlank
	private String effet;

	@NotBlank
	@Column(name = "temps_preparation")
	private String tempsPreparation;

	@NotBlank
	private String sd;

	@NotBlank
	private String duree;

	@NotBlank
	private String composant;

	@NotBlank
	private String niveau;

	//----------------------------------------------------------------------------------------------------------------//
	@OneToMany(mappedBy = "rituel")
	private List<RituelPersonnage> rituelPersonnageList = new ArrayList<>();

}
