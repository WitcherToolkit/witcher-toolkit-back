package fr.meya.witcher.message.response;

import fr.meya.witcher.domain.model.persistent.Personnage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParticulariteVolatile {

    private Long idParticularite;

    @NotBlank(message = "error.particularite.nom.required")
    @Size( max = 50)
    private String nom;

    @NotBlank(message = "error.particularite.description.required")
    private String description;

}
