package fr.meya.witcher.message.response;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompetenceVolatile {

	@NotBlank
	private String nom;

	@NotBlank
	private String codeCaracteristique;

	@NotBlank
	private String description;

	@NotBlank
	private String descriptionBase10;

	@NotBlank
	private String descriptionBase13;

	@NotBlank
	private String descriptionBase16;

	@NotBlank
	private String descriptionBase20;
}
