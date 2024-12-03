package fr.meya.witcher_toolkit_back.service;

import fr.meya.witcher_toolkit_back.model.Caracteristique;
import fr.meya.witcher_toolkit_back.repository.CaracteristiqueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaracteristiqueService {

	private final CaracteristiqueRepository caracteristiqueRepository;

	public CaracteristiqueService(CaracteristiqueRepository caracteristiqueRepository) {
		this.caracteristiqueRepository = caracteristiqueRepository;
	}

	public List<Caracteristique> getCaracteristiqueList(){
		return caracteristiqueRepository.list();
	}
}
