package fr.meya.witcher.model.persistent;

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
	@JoinColumn(name = "IDPROFESSION", nullable = false)
	private  Profession profession;

	@ManyToOne
	@JoinColumn(name = "IDPERSONNAGE", nullable = false)
	private Personnage personnage;

}
