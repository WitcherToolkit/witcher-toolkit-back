package fr.meya.witcher.domain.model.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class MagiePersonnageId {

    @Column(name = "id_personnage")
    private Long idPersonnage;

    @Column(name = "id_magie")
    private Long idMagie;
}
