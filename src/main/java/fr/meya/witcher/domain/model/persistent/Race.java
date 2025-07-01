package fr.meya.witcher.domain.model.persistent;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Classe décrivant une race
 */
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Race {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "IDRACE")
    private long idRace;

    @NotBlank
    private String nom;

    @OneToMany(mappedBy = "race", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER/*affiche automatiquement les éléments*/)
    private List<ReputationWiki> reputationWikiList;

    @OneToMany(mappedBy = "race", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER/*affiche automatiquement les éléments*/)
    private List<Particularite> particulariteList;

}
