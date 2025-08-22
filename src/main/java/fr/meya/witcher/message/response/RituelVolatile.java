package fr.meya.witcher.message.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RituelVolatile {

    private long idRituel;

    @NotBlank(message = "error.rituel.nom.required")
    @Size( max = 60)
    private String nom;

    @NotBlank(message = "error.rituel.cout.required")
    @Size( max = 10)
    private String cout;

    @NotBlank(message = "error.rituel.effet.required")
    private String effet;

    @NotBlank(message = "error.rituel.tempsPreparation.required")
    @Size( max = 10)
    private String tempsPreparation;

    @NotBlank(message = "error.rituel.sd.required")
    @Size( max = 10)
    private String sd;

    @NotBlank(message = "error.rituel.duree.required")
    @Size( max = 15)
    private String duree;

    @NotBlank(message = "error.rituel.composant.required")
    private String composant;

    @NotBlank(message = "error.rituel.niveau.required")
    @Size( max = 20)
    private String niveau;

}
