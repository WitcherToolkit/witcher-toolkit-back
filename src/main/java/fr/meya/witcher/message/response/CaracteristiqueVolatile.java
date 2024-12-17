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
