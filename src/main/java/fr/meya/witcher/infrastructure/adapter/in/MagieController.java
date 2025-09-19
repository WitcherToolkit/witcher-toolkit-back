package fr.meya.witcher.infrastructure.adapter.in;

import fr.meya.witcher.application.mapper.MagieMapper;
import fr.meya.witcher.domain.model.persistent.Magie;
import fr.meya.witcher.domain.port.in.IMagieService;
import fr.meya.witcher.message.response.MagieVolatile;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/magies")
public class MagieController {
    private final IMagieService iMagieService;
    private final MagieMapper magieMapper;

    public MagieController(IMagieService iMagieService, MagieMapper magieMapper) {
        this.iMagieService = iMagieService;
        this.magieMapper = magieMapper;
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<MagieVolatile>> listMagie(@RequestParam(required = false) String niveau) {
        log.info("consultation magie" + (niveau != null ? " filtrée par niveau : " + niveau : ""));
        List<MagieVolatile> result = iMagieService.getMagieList(niveau);
        return ResponseEntity.ok(result);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<MagieVolatile> updateMagie(@PathVariable Long id, @Valid @RequestBody MagieVolatile magieVolatile) {
        log.info("Modification de la magie - ID : {} - Données : {}", id, magieVolatile);
        Magie updatedMagie = iMagieService.updateMagie(id, magieVolatile);

        return ResponseEntity.ok(magieMapper.toMagieDto(updatedMagie));
    }

    @PostMapping("/create")
    public ResponseEntity<Magie> createMagie(@RequestBody MagieVolatile magieVolatile) {
        log.info("Ajout d'une magie");

        // Appel direct au service avec l'objet reçu
        Magie createdMagie = iMagieService.createMagie(magieVolatile);

        // Retourner la réponse
        return ResponseEntity.ok(createdMagie);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMagie(@PathVariable Long id) {
        log.info("Supprimer la magie avec l'ID : {}", id);

        iMagieService.deleteMagie(id);
        return ResponseEntity.noContent().build();
    }

}
