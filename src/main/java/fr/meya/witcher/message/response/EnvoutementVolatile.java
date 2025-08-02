package fr.meya.witcher.message.response;

import fr.meya.witcher.domain.model.enums.DangerEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnvoutementVolatile {

    @NotNull
    private long idEnvoutement;

    @NotBlank(message = "error.envoutement.nom.required")
    @Size( max = 60)
    private String nom;

    @NotBlank(message = "error.envoutement.cout.required")
    @Size( max = 10)
    private String cout;

    @NotBlank(message = "error.envoutement.effet.required")
    private String effet;

    @NotBlank(message = "error.envoutement.prerequis.required")
    private String prerequis;

    @NotNull(message = "error.envoutement.danger.required")
    private DangerEnum danger;

}
