package fr.meya.witcher.domain.model.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
@Setter
@Getter
public class RituelPersonnageId implements Serializable {

    @Column(name = "id_personnage")
    private UUID idPersonnage;

    @Column(name = "id_rituel")
    private UUID idRituel;
}
