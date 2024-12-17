package fr.meya.witcher.message.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CaracteristiqueVolatile {

	//private long idCaracteristique;

	@NotBlank
	@Size( max = 16)
	private String nom;

	@NotBlank
	@Size( max = 6)
	private String code;

	@NotBlank
	private String description;
}
