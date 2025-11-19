package fr.meya.witcher.message.response;

import fr.meya.witcher.domain.model.enums.TypeCaracteristiqueEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Caracteristiques volatiles
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CaracteristiqueVolatile {

	private UUID idCaracteristique;

	@NotBlank(message = "error.caracteristique.nom.required")
	@Size( max = 16)
	private String nom;

	@NotBlank(message = "error.caracteristique.code.required")
	@Size( max = 6)
	private String code;

	@NotBlank(message = "error.caracteristique.description.required")
	private String description;

	@NotNull(message = "error.caracteristique.type.required")
	private TypeCaracteristiqueEnum type;

}
