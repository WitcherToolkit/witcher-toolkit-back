package fr.meya.witcher.infrastructure.adapter.in;

import fr.meya.witcher.message.response.ProfessionVolatile;
import fr.meya.witcher.domain.port.in.IProfessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/profession")
public class ProfessionController {

    private final IProfessionService iProfessionService;

    public ProfessionController(IProfessionService iProfessionService) {
        this.iProfessionService = iProfessionService;
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<ProfessionVolatile>> listProfession() {
        log.info("consultation profession");
        List<ProfessionVolatile> result = iProfessionService.getListeDesProfessionsVolatiles();
        return ResponseEntity.ok(result);
    }

}

