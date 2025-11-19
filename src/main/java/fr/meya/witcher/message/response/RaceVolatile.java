package fr.meya.witcher.message.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RaceVolatile {

    private UUID idRace;

    @NotBlank(message = "error.race.nom.required")
    @Size( max = 50)
    private String nom;

    private List<ReputationWikiVolatile> reputationWikiList;

    private List<ParticulariteVolatile> particulariteList;
    
}
