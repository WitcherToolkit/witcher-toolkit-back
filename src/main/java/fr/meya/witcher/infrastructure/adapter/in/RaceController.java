package fr.meya.witcher.infrastructure.adapter.in;

import fr.meya.witcher.application.mapper.RaceMapper;
import fr.meya.witcher.domain.port.in.IRaceService;
import fr.meya.witcher.message.response.RaceVolatile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
