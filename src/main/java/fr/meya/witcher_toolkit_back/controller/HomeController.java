package fr.meya.witcher_toolkit_back.controller;

import fr.meya.witcher_toolkit_back.service.CaracteristiqueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class HomeController {

	private final CaracteristiqueService caracteristiqueService;

	public HomeController(CaracteristiqueService caracteristiqueService) {
		this.caracteristiqueService = caracteristiqueService;
	}

	@GetMapping("/")
	public String displayHome(Model model) {
		log.info("page d'accueil");
		model.addAttribute("caracteristiques", caracteristiqueService.getCaracteristiqueList());
		return "witcher-home";
	}
}
