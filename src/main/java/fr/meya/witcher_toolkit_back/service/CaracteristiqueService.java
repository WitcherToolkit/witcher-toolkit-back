package fr.meya.witcher_toolkit_back.service;

import fr.meya.witcher_toolkit_back.model.persistent.Caracteristique;
import fr.meya.witcher_toolkit_back.port.out.ICaracteristiqueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaracteristiqueService implements ICaracteristiqueService{

	private final ICaracteristiqueRepository caracteristiqueRepository;

	public CaracteristiqueService(ICaracteristiqueRepository caracteristiqueRepository) {
		this.caracteristiqueRepository = caracteristiqueRepository;
	}

	@Override
	public Caracteristique createCaracteristique(Caracteristique caracteristique) {

		return caracteristique;
	}

	@Override
	public List<Caracteristique> getCaracteristiqueList(){
		return caracteristiqueRepository.findAll();
	}

}
