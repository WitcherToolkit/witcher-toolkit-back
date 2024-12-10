package fr.meya.witcher_toolkit_back.model.persistent;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Profession {

	private int id;

	private String nom;

	private String competenceExclusive;

	private CompetenceSpecifique competenceSpecifique;

}
