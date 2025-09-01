package fr.meya.witcher.domain.model.persistent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.meya.witcher.domain.model.key.RituelPersonnageId;
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
@Table(name = "rituel_personnage")
public class RituelPersonnage {

    @EmbeddedId
    private RituelPersonnageId idRituelPersonnage;

    @ManyToOne
    @JsonIgnore
    @MapsId("idPersonnage")
    @JoinColumn(name = "id_personnage", nullable = false, foreignKey = @ForeignKey(name = "fk_rituel_personnage_personnage"))
    private Personnage personnage;

    @ManyToOne
    @MapsId("idRituel")
    @JoinColumn(name = "id_rituel", nullable = false, foreignKey = @ForeignKey(name = "fk_rituel_personnage_rituel"))
    private Rituel rituel;
}
