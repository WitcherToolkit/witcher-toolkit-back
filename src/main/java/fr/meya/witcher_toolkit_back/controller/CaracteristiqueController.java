package fr.meya.witcher_toolkit_back.controller;

import fr.meya.witcher_toolkit_back.model.Caracteristique;
import fr.meya.witcher_toolkit_back.service.CaracteristiqueService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/caracteristique")
public class CaracteristiqueController {

	private final CaracteristiqueService caracteristiqueService;

	public CaracteristiqueController(CaracteristiqueService caracteristiqueService) {
		this.caracteristiqueService = caracteristiqueService;
	}

	@GetMapping
	public String displayCaracteristique(Model model) {
		log.info("consultation caracteristique");
		model.addAttribute("caracteristiques", caracteristiqueService.getCaracteristiqueList());
		return "caracteristique/caracteristique-consultation";
	}

	// Pour afficher le formulaire
	@GetMapping("/add")
	public String showAddForm(Model model) {
		model.addAttribute("caracteristique", new Caracteristique());
		return "caracteristique/caracteristique-creation";
	}

	// Pour traiter la soumission du formulaire
	@PostMapping("/add")
	public String addCaracteristique(@Valid @ModelAttribute Caracteristique result, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "caracteristique/caracteristique-creation";
		}

		Caracteristique caracteristique = new Caracteristique();
		caracteristique.setNom(result.getNom());
		caracteristique.setCode(result.getCode());
		caracteristique.setDescription(result.getDescription());

		caracteristiqueService.getCaracteristiqueList().add(caracteristique);

		// Au lieu d'ajouter directement à la liste, assurez-vous que l'ajout passe par le service
		//caracteristiqueService.getCaracteristiqueList().add(caracteristique);

		//return "redirect:/caracteristique";

		return "caracteristique/caracteristique-consultation";
	}
}
