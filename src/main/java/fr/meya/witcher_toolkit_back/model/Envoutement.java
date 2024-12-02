package fr.meya.witcher_toolkit_back.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Envoutement {

	private int id;

	private String nom;

	private String cout;

	private String effet;

	// voir si on ajoute "danger" ou encore "prérequis pour lever la malédiction"

}
