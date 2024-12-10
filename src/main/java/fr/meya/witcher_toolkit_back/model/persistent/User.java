package fr.meya.witcher_toolkit_back.model.persistent;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class User {

	private int id;

	private String pseudo;

	private String email;

	private String password;

	private Boolean isAdmin;

	private List<Personnage> personnageList;

	private List<Campagne> campagneList;
}
