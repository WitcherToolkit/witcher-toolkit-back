package fr.meya.witcher.domain.model.persistent;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Particularite {

    @PrePersist
    public void generateId() {
        if (this.idParticularite == null) {
            this.idParticularite = UUID.randomUUID();
        }
    }

    @Id
    @Column(name = "id_Particularite")
    private UUID idParticularite;

    private String nom;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_race", nullable = false, foreignKey = @ForeignKey(name = "fk_particularite_race"))
    private Race race;

}
