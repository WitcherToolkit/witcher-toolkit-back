package fr.meya.witcher.message.response;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RituelVolatile {
    @NotBlank
    private long idRituel;
    @NotBlank
    private String nom;
    @NotBlank
    private String cout;
    @NotBlank
    private String effet;
    @NotBlank
    private String tempsPreparation;
    @NotBlank
    private String sd;
    @NotBlank
    private String duree;
    @NotBlank
    private String composant;
    @NotBlank
    private String niveau;
}
