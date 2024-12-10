package fr.meya.witcher_toolkit_back.model.persistent;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CompetenceSpecifiquePersonnage {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "idCompetenceSpecifique", nullable = false)
	private CompetenceSpecifique competenceSpecifique;

	@ManyToOne
	@JoinColumn(name = "idPersonnage", nullable = false)
	private Personnage personnage;

	private int valeurMax;

	private int valeurActuelle;
}
