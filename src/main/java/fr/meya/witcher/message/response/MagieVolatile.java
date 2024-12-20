package fr.meya.witcher.message.response;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MagieVolatile {

    @NotBlank
    private String nom;

    @NotBlank
    private String cout;

    @NotBlank
    private String effet;

    private String portee;

    @NotBlank
    private String duree;

    private String element;

    @NotBlank
    private String niveau;

    private String contre;

    @NotBlank
    private String profession;

}
