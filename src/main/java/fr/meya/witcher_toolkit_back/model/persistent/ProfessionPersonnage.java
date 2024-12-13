package fr.meya.witcher_toolkit_back.model.persistent;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ProfessionPersonnage {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private long idProfessionPersonnage;

	private int valeurActuelle;

	private  int valeurMax;

	@ManyToOne
	@JoinColumn(name = "idProfession", nullable = false)
	private  Profession profession;

	@ManyToOne
	@JoinColumn(name = "idPersonnage", nullable = false)
	private Personnage personnage;

}
