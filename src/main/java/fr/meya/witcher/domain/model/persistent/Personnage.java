package fr.meya.witcher.domain.model.persistent;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
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
	@Column(name = "id_personnage")
	private long idPersonnage;

	@Column(name = "nom_joueur")
	private String nomJoueur;

	@Column(name = "nom_personnage")
	private String nomPersonnage;

	@Column(name = "nom_image")
	private String nomImage;

	@Column(name = "url_image")
	private String urlImage;

	private String genre;

	@Column(name = "terre_natale")
	private String terreNatale;

	private String xp;

	private long age;

	private boolean bestiaire;

	private String historique;

	private String poings;

	private String pieds;
	//----------------------------------------------------------------------------------------------------------------//

	@ManyToOne
	@JoinColumn(name = "id_race", nullable = false)
	private Race race;

	@ManyToOne
	@JoinColumn(name = "id_campagne")
	private Campagne campagne;

	@ManyToOne
	@JoinColumn(name = "id_profil_utilisateur")
	private ProfilUtilisateur profilUtilisateur;

	@OneToMany(mappedBy = "personnage")
	private List<CaracteristiquePersonnage> caracteristiquePersonnageList = new ArrayList<>();

	@OneToMany(mappedBy = "personnage")
	private List<CompetencePersonnage> competencePersonnageList = new ArrayList<>();

	@OneToMany(mappedBy = "personnage")
	private List<RituelPersonnage> rituelPersonnageList = new ArrayList<>();

	@OneToMany(mappedBy = "personnage")
	private List<EnvoutementPersonnage> envoutementPersonnageList = new ArrayList<>();

}
