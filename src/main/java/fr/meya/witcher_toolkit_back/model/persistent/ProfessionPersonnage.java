package fr.meya.witcher_toolkit_back.model.persistent;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ProfessionPersonnage {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private int id;

	private  Profession profession;

	private Personnage personnage;

	private  int valeurMax;

	private int valeurActuelle;
}
