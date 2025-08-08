package fr.meya.witcher.domain.model.persistent;

import jakarta.persistence.*;
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
	@Column(name = "id_personnage")
	private long idPersonnage;

	private String nomJoueur;

	private String nomPersonnage;

	private String nomImage;

	private String urlImage;

	private String genre;

	private String terreNatale;

	private String xp;

	private long age;

	private boolean isBestiaire;

	@ManyToOne
	@JoinColumn(name = "id_race", nullable = false)
	private Race race;

	@ManyToOne
	@JoinColumn(name = "id_campagne")
	private Campagne campagne;

	@ManyToOne
	@JoinColumn(name = "id_profil_utilisateur")
	private ProfilUtilisateur profilUtilisateur;

	//----------------------------------------------------------------------------------------------------------------//

	@OneToMany(mappedBy = "personnage", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CaracteristiquePersonnage> caracteristiquePersonnageList;

	@OneToMany(mappedBy = "personnage", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CompetencePersonnage> competencePersonnageList;

	@ManyToMany(mappedBy = "personnageList")
	private List<Rituel> rituelList;

	@ManyToMany(mappedBy = "personnageList")
	private List<Envoutement> envoutementList;

}
