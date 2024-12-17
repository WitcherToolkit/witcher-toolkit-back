package fr.meya.witcher.adapter.port.in;

import fr.meya.witcher.model.message.CaracteristiqueVolatile;
import fr.meya.witcher.model.persistent.Caracteristique;
import fr.meya.witcher.service.ICaracteristiqueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@PutMapping("/update/{id}")
	public ResponseEntity<Caracteristique> updateCaracteristique( @PathVariable Long id, @RequestBody CaracteristiqueVolatile caracteristiqueVolatile) {
		log.info("modifier une caractéristique");
		Caracteristique updatedCaracteristique = iCaracteristiqueService.updateCaracteristique(id, caracteristiqueVolatile);
		return ResponseEntity.ok(updatedCaracteristique);
	}}

