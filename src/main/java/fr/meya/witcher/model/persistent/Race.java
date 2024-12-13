package fr.meya.witcher.model.persistent;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Race {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int idRace;

    @NotBlank
    private String nom;

    @NotBlank
    private String categorie;

}
