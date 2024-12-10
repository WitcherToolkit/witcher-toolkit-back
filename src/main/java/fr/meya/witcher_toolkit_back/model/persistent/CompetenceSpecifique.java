package fr.meya.witcher_toolkit_back.model.persistent;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CompetenceSpecifique  extends Competence {

	@ManyToOne
	private Profession profession;

	private String specialisation;

	private String presrequis;

}
