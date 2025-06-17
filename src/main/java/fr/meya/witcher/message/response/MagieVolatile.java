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
    private long idMagie;

    @NotBlank
    private String nom;

    @NotBlank
    private String cout;

    @NotBlank
    private String effet;

    private String portee;

    @NotBlank
    private String duree;

    private String nature;

    //Si c'est un sort, une invocation, ou un signe
    private String type;

    @NotBlank
    private String niveau;

    private String contre;

}
