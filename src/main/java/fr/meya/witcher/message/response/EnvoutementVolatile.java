package fr.meya.witcher.message.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnvoutementVolatile {

    private String nom;

    private String cout;

    private String effet;

    private String prerequis;

    private String danger;

}
