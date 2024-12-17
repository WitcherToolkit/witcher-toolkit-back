package fr.meya.witcher.domain.model.persistent;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Race {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "IDRACE")
    private long idRace;

    @NotBlank
    private String nom;

    @NotBlank
    private String categorie;

}
