package fr.meya.witcher.message.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParticulariteVolatile {

    private UUID idParticularite;

    @NotBlank(message = "error.particularite.nom.required")
    @Size( max = 50)
    private String nom;

    @NotBlank(message = "error.particularite.description.required")
    private String description;

}
