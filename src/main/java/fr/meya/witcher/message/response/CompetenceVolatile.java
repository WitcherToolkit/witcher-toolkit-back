package fr.meya.witcher.message.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompetenceVolatile {

	private UUID idCompetence;

	@NotBlank(message = "error.competence.nom.required")
	@Size( max = 50)
	private String nom;

	@NotBlank(message = "error.competence.description.required")
	private String description;

	//@NotBlank(message = "error.competence.exclusive.required")
    private boolean exclusive;

	@Size( max = 20)
	private String prerequis;

	@Size( max = 20)
	private String specialisation;

	private int step;

	private String type;

	@NotBlank(message = "error.competence.nom.required")
	private CaracteristiqueVolatile caracteristique;

}
