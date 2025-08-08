package fr.meya.witcher.domain.model.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class CaracteristiquePersonnageId implements Serializable {

    @Column(name = "id_personnage")
    private Long idPersonnage;

    @Column(name = "id_caracteristique")
    private Long idCaracteristique;

}
