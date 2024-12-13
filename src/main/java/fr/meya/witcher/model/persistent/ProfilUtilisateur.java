package fr.meya.witcher.model.persistent;

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
public class ProfilUtilisateur {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private long idProfilUtilisateur;

	private String pseudo;

	private String email;

	private String password;

	private Boolean isAdmin;

	@OneToMany(mappedBy = "user")
	private List<Personnage> personnageList;

	@OneToMany(mappedBy = "user")
	private List<Campagne> campagneList;
}
