package fr.meya.witcher.infrastructure.adapter.in;

import fr.meya.witcher.application.mapper.CaracteristiqueMapper;
import fr.meya.witcher.domain.model.persistent.Caracteristique;
import fr.meya.witcher.domain.port.in.ICaracteristiqueService;
import fr.meya.witcher.message.response.CaracteristiqueVolatile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/caracteristiques")
public class CaracteristiqueController {

	private final ICaracteristiqueService iCaracteristiqueService;
	private final CaracteristiqueMapper caracteristiqueMapper;

	public CaracteristiqueController(ICaracteristiqueService iCaracteristiqueService, CaracteristiqueMapper caracteristiqueMapper) {
		this.iCaracteristiqueService = iCaracteristiqueService;
		this.caracteristiqueMapper = caracteristiqueMapper;
	}

	@GetMapping(value = "/list")
	public ResponseEntity<List<CaracteristiqueVolatile>> listCaracteristique() {
		log.info("consultation caracteristique");
		List<CaracteristiqueVolatile> result = iCaracteristiqueService.getCaracteristiqueList();
		return ResponseEntity.ok(result);
	}

	@PutMapping(value = "/update/{id}")
	public ResponseEntity<CaracteristiqueVolatile> updateCaracteristique(@PathVariable UUID id, @RequestBody CaracteristiqueVolatile caracteristiqueVolatile) {
		log.info("Modification de la caracteristique - ID : {} - Données : {}", id, caracteristiqueVolatile);
		Caracteristique updatedCaracteristique = iCaracteristiqueService.updateCaracteristique(id, caracteristiqueVolatile);

		return ResponseEntity.ok(caracteristiqueMapper.toCaracteristiqueDto(updatedCaracteristique));
	}

	@PostMapping("/create")
	public ResponseEntity<Caracteristique> createCaracteristique(@RequestBody CaracteristiqueVolatile caracteristiqueVolatile) {
		log.info("Créer une caractéristique");

		Caracteristique createdCaracteristique = iCaracteristiqueService.createCaracteristique(caracteristiqueVolatile);
		return ResponseEntity.ok(createdCaracteristique);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteCaracteristique(@PathVariable UUID id) {
		log.info("Supprimer la caractéristique avec l'ID : {}", id);
		iCaracteristiqueService.deleteCaracteristique(id);
		// Retourne la caractéristique supprimée dans la réponse
		return ResponseEntity.noContent().build();
	}

}

