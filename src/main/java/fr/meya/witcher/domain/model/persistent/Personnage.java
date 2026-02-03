package fr.meya.witcher.domain.model.persistent;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Classe décrivant un personnage
 */
@Getter
@Setter
@Entity
public class Personnage {

    @PrePersist
    public void generateId() {
        if (this.idPersonnage == null) {
            this.idPersonnage = UUID.randomUUID();
        }
    }

	@Id
	@Column(name = "id_personnage")
	private UUID idPersonnage;

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

	private Integer xp;

	private long age;

	private boolean bestiaire;

	private String historique;

	private String poings;

	private String pieds;

	//----------------------------------------------------------------------------------------------------------------//
    @ManyToOne
    @JoinColumn(name = "id_profession", nullable = false)
    private Profession profession;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_race", nullable = false, foreignKey = @ForeignKey(name = "fk_personnage_race"))
	private Race race;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_campagne", foreignKey = @ForeignKey(name = "fk_personnage_campagne"))
	private Campagne campagne;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_user", foreignKey = @ForeignKey(name = "fk_personnage_user"))
	private User user;

	@OneToMany(mappedBy = "personnage")
	private List<CaracteristiquePersonnage> caracteristiquePersonnageList = new ArrayList<>();

    @OneToMany(mappedBy = "personnage", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CompetencePersonnage> competencePersonnageList = new ArrayList<>();

    @OneToMany(mappedBy = "personnage", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<RituelPersonnage> rituelPersonnageList = new ArrayList<>();

    @OneToMany(mappedBy = "personnage", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<EnvoutementPersonnage> envoutementPersonnageList = new ArrayList<>();

    @OneToMany(mappedBy = "personnage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MagiePersonnage> magiePersonnageList = new ArrayList<>();

    @OneToMany(mappedBy = "personnage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Inventaire>  inventaireList = new ArrayList<>();

}
