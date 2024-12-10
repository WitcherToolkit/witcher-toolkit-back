package fr.meya.witcher_toolkit_back.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompetenceSpecifique  extends Competence {

	private Profession profession;

	private String specialisation;

	private String presrequis;

}
