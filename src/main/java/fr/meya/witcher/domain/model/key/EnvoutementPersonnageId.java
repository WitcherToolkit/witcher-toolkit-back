package fr.meya.witcher.domain.model.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
@Setter
@Getter
public class EnvoutementPersonnageId {

    @Column(name = "id_personnage")
    private Long idPersonnage;

    @Column(name = "id_envoutement")
    private Long idEnvoutement;
}
