package fr.meya.witcher.message.response;

import fr.meya.witcher.domain.model.enums.NatureMagieEnum;
import fr.meya.witcher.domain.model.enums.TypeMagieEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MagieVolatile {

    private UUID idMagie;

    @NotBlank(message = "error.magie.nom.required")
    @Size(max = 60, message = "{error.max.size}") //Mise en paramètre de la taille max pour le message d'erreur
    private String nom;

    @NotBlank(message = "error.magie.cout.required")
    @Size(max = 10, message = "{error.max.size}")
    private String cout;

    @NotBlank(message = "error.magie.effet.required")
    private String effet;

    @Size(max = 20, message = "{error.max.size}")
    private String portee;

    @NotBlank(message = "error.magie.duree.required")
    @Size(max = 35, message = "{error.max.size}")
    private String duree;

    @NotNull(message = "error.magie.nature.required")
    private NatureMagieEnum nature;

    private TypeMagieEnum type;

    @NotBlank(message = "error.magie.niveau.required")
    @Size(max = 35, message = "{error.max.size}")
    private String niveau;

    @Size(max = 25, message = "{error.max.size}")
    private String contre;

}
