package fr.meya.witcher.model.persistent;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Profession {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProfession;

	private String nom;

	private String competenceExclusive;

	@OneToMany(mappedBy = "personnage", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CompetenceSpecifique> competenceSpecifique;


}
