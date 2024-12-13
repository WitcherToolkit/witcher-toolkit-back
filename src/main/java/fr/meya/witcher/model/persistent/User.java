package fr.meya.witcher.model.persistent;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class User {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private int idUser;

	private String pseudo;

	private String email;

	private String password;

	private Boolean isAdmin;

	private List<Personnage> personnageList;

	private List<Campagne> campagneList;
}
