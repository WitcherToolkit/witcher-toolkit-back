package fr.meya.witcher.message.response;

import fr.meya.witcher.domain.model.persistent.Personnage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParticulariteVolatile {

    private Long idParticularite;

    private String nom;

    private String description;

}
