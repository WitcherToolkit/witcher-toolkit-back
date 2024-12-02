package fr.meya.witcher_toolkit_back.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Campagne géré par les utilisateurs.
 */
@Getter
@Setter
public class Campagne {

	private int id;

	private String nom;

	private  User user;

	private List<Personnage> personnageList;

	//private Boolean isStart;
}
