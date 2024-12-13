package fr.meya.witcher.adapter.port.in;

import fr.meya.witcher.model.persistent.Caracteristique;
import fr.meya.witcher.service.ICaracteristiqueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
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
	public List<Caracteristique> list() {
		log.info("consultation caracteristique");
		return iCaracteristiqueService.getCaracteristiqueList();
	}

}

