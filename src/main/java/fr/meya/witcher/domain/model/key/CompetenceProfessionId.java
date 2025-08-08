package fr.meya.witcher.domain.model.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

/**
 * Classe représentant la clé primaire composite de la table competence_profession.
 * Elle combine les identifiants de Profession et de Competence.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class CompetenceProfessionId implements Serializable {

    @Column(name = "id_profession")
    private Long idProfession;

    @Column(name = "id_competence")
    private Long idCompetence;

}
