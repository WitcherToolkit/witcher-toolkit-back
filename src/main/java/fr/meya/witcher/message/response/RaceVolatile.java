package fr.meya.witcher.message.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RaceVolatile {
    private Long idRace;

    private String nom;

    private List<ReputationWikiVolatile> reputationWikiList;

    private List<ParticulariteVolatile> particulariteList;
    
}
