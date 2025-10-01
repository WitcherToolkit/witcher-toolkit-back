package fr.meya.witcher.domain.model.persistent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.meya.witcher.domain.model.key.MagiePersonnageId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "magie_personnage")
public class MagiePersonnage {
    @EmbeddedId
    private MagiePersonnageId id;

    @ManyToOne
    @JsonIgnore
    @MapsId("idPersonnage")
    @JoinColumn(name = "id_personnage", nullable = false, foreignKey = @ForeignKey(name = "fk_magie_personnage_personnage"))
    private Personnage personnage;

    @ManyToOne
    @MapsId("idMagie")
    @JoinColumn(name = "id_magie", nullable = false, foreignKey = @ForeignKey(name = "fk_magie_personnage_magie"))
    private Magie magie;

}
