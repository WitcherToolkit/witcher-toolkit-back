package fr.meya.witcher.infrastructure.adapter.in;

import fr.meya.witcher.message.response.CaracteristiqueVolatile;
import fr.meya.witcher.domain.model.persistent.Caracteristique;
import fr.meya.witcher.domain.port.in.ICaracteristiqueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/caracteristique")
public class CaracteristiqueController {

	private final ICaracteristiqueService iCaracteristiqueService;

	public CaracteristiqueController(ICaracteristiqueService iCaracteristiqueService) {
		this.iCaracteristiqueService = iCaracteristiqueService;
	}

	@GetMapping(value = "/list")
	public List<Caracteristique> listCaracteristique() {
		log.info("consultation caracteristique");
		return iCaracteristiqueService.getCaracteristiqueList();
	}

	@PostMapping("/create")
	public ResponseEntity<Caracteristique> createCaracteristique(@RequestBody CaracteristiqueVolatile caracteristiqueVolatile) {
		log.info("Créer une caractéristique");

		// Appel direct au service avec l'objet reçu
		Caracteristique createdCaracteristique = iCaracteristiqueService.createCaracteristique(caracteristiqueVolatile);

		// Retourner la réponse
		return ResponseEntity.ok(createdCaracteristique);
	}

	@PutMapping(value = "/update/{id}")
	public ResponseEntity<Caracteristique> updateCaracteristique( @PathVariable Long id, @RequestBody CaracteristiqueVolatile caracteristiqueVolatile) {
		log.info("modifier une caractéristique");
		Caracteristique updatedCaracteristique = iCaracteristiqueService.updateCaracteristique(id, caracteristiqueVolatile);
		return ResponseEntity.ok(updatedCaracteristique);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Caracteristique> deleteCaracteristique(@PathVariable Long id) {
		log.info("Supprimer la caractéristique avec l'ID : {}", id);
		Caracteristique deletedCaracteristique = iCaracteristiqueService.deleteCaracteristique(id);
		// Retourne la caractéristique supprimée dans la réponse
		return ResponseEntity.ok(deletedCaracteristique);
	}

}

