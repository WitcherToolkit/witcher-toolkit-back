package fr.meya.witcher.domain.model.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import org.checkerframework.checker.units.qual.N;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
@Setter
@Getter
public class RituelPersonnageId implements Serializable {

    @Column(name = "id_personnage")
    private Long idPersonnage;

    @Column(name = "id_rituel")
    private Long idRituel;
}
