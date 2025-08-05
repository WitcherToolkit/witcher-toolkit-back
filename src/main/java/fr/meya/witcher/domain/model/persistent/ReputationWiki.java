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
@Table(name = "REPUTATIONWIKI")
public class ReputationWiki {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "IDREPUTATIONWIKI")
    private Long idReputationWiki;

    private String territoire;

    private String valeur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDRACE", nullable = false)
    private Race race;
}
