package fr.meya.witcher_toolkit_back.model.persistent;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Rituel {

	private int id;

	private String nom;

	private String niveau;

	private String cout;

	private String effet;

	private String temps;

	private String sd;

	private String duree;

	private String composant;

}
