package fr.meya.witcher_toolkit_back.adapter.port.in;

import fr.meya.witcher_toolkit_back.model.persistent.Caracteristique;
import fr.meya.witcher_toolkit_back.service.ICaracteristiqueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

	// Pour traiter la soumission du formulaire
	@PostMapping
	public Caracteristique create(@RequestBody Caracteristique caracteristique) {
		return iCaracteristiqueService.createCaracteristique(caracteristique);
	}

	@GetMapping
	public List<Caracteristique> list() {
		log.info("consultation caracteristique");
		return iCaracteristiqueService.getCaracteristiqueList();
	}

	// Pour afficher le formulaire
	@GetMapping("/add")
	public String showAddForm(Model model) {
		model.addAttribute("caracteristique", new Caracteristique());
		return "caracteristique/caracteristique-creation";
	}


}

