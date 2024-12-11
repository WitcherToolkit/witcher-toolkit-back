package fr.meya.witcher_toolkit_back.model.persistent;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Classe décrivant un personnage
 */
@Getter
@Setter
@Entity
public class Personnage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPersonnage;

	private String nomJoueur;

	private String nomPersonnage;

	private String nomImage;

	private String urlImage;

	private String genre;

	private String terreNatale;

	private String xp;

	private int age;

	private boolean isBestiaire;

	@ManyToOne
	@JoinColumn(name = "idProfessionPersonnage")
	private ProfessionPersonnage professionPersonnage;

	@ManyToOne
	@JoinColumn(name = "idRace", nullable = false)
	private Race race;

	@ManyToOne
	@JoinColumn(name = "idCampagne")
	private Campagne campagne;

	@ManyToOne
	@JoinColumn(name = "idUser")
	private User user;

	//-----------------------------------------------//
	@OneToMany(mappedBy = "personnage", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CompetenceSpecifiquePersonnage> competenceSpecifiquePersonnageList;

	@OneToMany(mappedBy = "personnage", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CaracteristiquePersonnage> caracteristiquePersonnageList;

	@OneToMany(mappedBy = "personnage", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CompetencePersonnage> competencePersonnageList;

	@ManyToMany(mappedBy = "personnageList")
	private List<Rituel> rituelList;

	@ManyToMany(mappedBy = "personnageList")
	private List<Envoutement> envoutementList;


}
