package fr.meya.witcher_toolkit_back.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Magie {

	private int id;

	private String nom;

	private String effet;

	private String portee;

	private String element;

	private String duree;

	private String contre;
}
