package fr.meya.witcher.infrastructure.adapter.in;

import fr.meya.witcher.application.mapper.RaceMapper;
import fr.meya.witcher.domain.model.persistent.Race;
import fr.meya.witcher.domain.port.in.IRaceService;
import fr.meya.witcher.message.response.RaceVolatile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/races")
public class RaceController {
    private final IRaceService iRaceService;
    private final RaceMapper raceMapper;

    public RaceController(IRaceService iRaceService, RaceMapper raceMapper) {
        this.iRaceService = iRaceService;
        this.raceMapper = raceMapper;
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<RaceVolatile>> listRace() {

        log.info("consultation race");
        List<RaceVolatile> result = iRaceService.getRaceList();

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RaceVolatile> getRaceById(@PathVariable UUID id) {

        log.info("Consultation de la race avec l'ID : {}", id);
        Race race = iRaceService.getRaceById(id);
        RaceVolatile raceDto = raceMapper.toRaceDto(race);

        return ResponseEntity.ok(raceDto);
    }

    @PostMapping("/create")
    public ResponseEntity<RaceVolatile> createRace(@RequestBody RaceVolatile raceVolatile) {
        log.info("Créer une caractéristique");

        Race createdRace = iRaceService.createRace(raceVolatile);
        return ResponseEntity.ok(raceMapper.toRaceDto(createdRace));
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<RaceVolatile> updateRace(@PathVariable UUID id, @RequestBody RaceVolatile raceVolatile) {

        log.info("Modification de la race - ID : {} - Données : {}", id, raceVolatile);
        Race updatedRace = iRaceService.updateRace(id, raceVolatile);

        return ResponseEntity.ok(raceMapper.toRaceDto(updatedRace));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRace(@PathVariable UUID id) {
        log.info("Supprimer le race avec l'ID : {}", id);

        iRaceService.deleteRace(id);
        return ResponseEntity.noContent().build();
    }
}
