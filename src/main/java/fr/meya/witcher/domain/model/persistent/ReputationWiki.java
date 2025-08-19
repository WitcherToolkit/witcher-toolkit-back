package fr.meya.witcher.domain.model.persistent;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank
    @Size(max = 20)
    private String territoire;

    @NotBlank
    @Size(max = 20)
    private String valeur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_race", nullable = false)
    private Race race;
}
