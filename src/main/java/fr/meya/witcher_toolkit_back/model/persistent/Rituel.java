package fr.meya.witcher_toolkit_back.model.persistent;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Rituel {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private int id;

	private String nom;

	private String niveau;

	private String cout;

	private String effet;

	private String temps;

	private String sd;

	private String duree;

	private String composant;

	@ManyToMany
	@JoinTable(
			name = "personnageRituel",
			joinColumns = @JoinColumn(name = "idRituel"),
			inverseJoinColumns = @JoinColumn(name = "idPersonnage"))
	private List<Personnage> personnageList;

}
