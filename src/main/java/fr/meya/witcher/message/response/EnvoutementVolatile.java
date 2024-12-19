package fr.meya.witcher.message.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnvoutementVolatile {

    @NotBlank
    @Size( max = 30)
    private String nom;

    @NotBlank
    @Size( max = 10)
    private String cout;

    @NotBlank
    private String effet;

    @NotBlank
    private String prerequis;

    @NotBlank
    @Size( max = 6)
    private String danger;

}
