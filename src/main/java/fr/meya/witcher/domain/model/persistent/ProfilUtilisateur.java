package fr.meya.witcher.domain.model.persistent;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "profil_utilisateur")
public class ProfilUtilisateur {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column(name = "id_user")
	private long idProfilUtilisateur;

	private String pseudo;

	private String email;

	private String password;

	@Column(name = "is_admin")
	private Boolean isAdmin;

	@OneToMany(mappedBy = "profil_utilisateur")
	private List<Personnage> personnageList;

	@OneToMany(mappedBy = "profil_utilisateur")
	private List<Campagne> campagneList;

}
