package fr.meya.witcher.domain.model.persistent;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reputation_wiki")
public class ReputationWiki {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "id_reputation_wiki")
    private Long idReputationWiki;

    private String territoire;

    private String valeur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_race", nullable = false)
    private Race race;
}
