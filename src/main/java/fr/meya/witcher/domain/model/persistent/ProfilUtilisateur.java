package fr.meya.witcher.domain.model.persistent;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class ProfilUtilisateur {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column(name = "IDPROFILUTILISATEUR")
	private long idProfilUtilisateur;

	private String pseudo;

	private String email;

	private String password;

	private Boolean isAdmin;

	@OneToMany(mappedBy = "profilUtilisateur")
	private List<Personnage> personnageList;

	@OneToMany(mappedBy = "profilUtilisateur")
	private List<Campagne> campagneList;
}
