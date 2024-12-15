package fr.meya.witcher.adapter.port.in;

import fr.meya.witcher.model.persistent.Profession;
import fr.meya.witcher.service.IProfessionService;
import lombok.extern.slf4j.Slf4j;
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
    public List<Profession> listProfession() {
        log.info("consultation profession");
        return iProfessionService.getProfessionList();
    }

}

