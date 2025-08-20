package fr.meya.witcher.domain.model.persistent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.meya.witcher.domain.model.key.EnvoutementPersonnageId;
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
@Table(name = "envoutement_personnage")
public class EnvoutementPersonnage {
    @EmbeddedId
    private EnvoutementPersonnageId idEnvoutementPersonnage;

    @ManyToOne
    @JsonIgnore
    @MapsId("idPersonnage")
    @JoinColumn(name = "id_personnage", nullable = false)
    private Personnage personnage;

    @ManyToOne
    @MapsId("idEnvoutement")
    @JoinColumn(name = "id_envoutement", nullable = false)
    private Envoutement envoutement;
}
