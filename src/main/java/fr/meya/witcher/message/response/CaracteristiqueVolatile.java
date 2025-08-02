package fr.meya.witcher.message.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CaracteristiqueVolatile {

	@NotBlank
	private long idCaracteristique;

	@NotBlank(message = "error.caracteristique.nom.required")
	@Size( max = 16)
	private String nom;

	@NotBlank(message = "error.caracteristique.code.required")
	@Size( max = 6)
	private String code;

	@NotBlank(message = "error.caracteristique.description.required")
	private String description;

}
