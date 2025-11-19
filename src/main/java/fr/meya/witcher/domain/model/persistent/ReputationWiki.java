package fr.meya.witcher.domain.model.persistent;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
@Table(name = "reputation_wiki")
public class ReputationWiki {

    @PrePersist
    public void generateId() {
        if (this.idReputationWiki == null) {
            this.idReputationWiki = UUID.randomUUID();
        }
    }

    @Id
    @Column(name = "id_reputation_wiki")
    private UUID idReputationWiki;

    @NotBlank
    @Size(max = 20)
    private String territoire;

    @NotBlank
    @Size(max = 20)
    private String valeur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_race", nullable = false, foreignKey = @ForeignKey(name = "fk_reputation_wiki_race"))
    private Race race;
}
