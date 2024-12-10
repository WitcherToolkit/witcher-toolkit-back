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
public class Envoutement {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private int id;

	private String nom;

	private String cout;

	private String effet;

	@ManyToMany
	@JoinTable(
			name = "personnageEnvoutement", // Nom de la table de jointure
			joinColumns = @JoinColumn(name = "idEnvoutement"), // Colonne représentant l'entité Envoutement
			inverseJoinColumns = @JoinColumn(name = "idPersonnage") // Colonne représentant l'entité Personnage
	)
	private List<Personnage> personnageList;

	// voir si on ajoute "danger" ou encore "prérequis pour lever la malédiction"

}
