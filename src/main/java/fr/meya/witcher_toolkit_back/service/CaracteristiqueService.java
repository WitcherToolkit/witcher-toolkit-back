package fr.meya.witcher_toolkit_back.service;

import fr.meya.witcher_toolkit_back.model.persistent.Caracteristique;
import fr.meya.witcher_toolkit_back.adapter.port.out.CaracteristiqueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaracteristiqueService implements ICaracteristiqueService{

	private final CaracteristiqueRepository caracteristiqueRepository;

	public CaracteristiqueService(CaracteristiqueRepository caracteristiqueRepository) {
		this.caracteristiqueRepository = caracteristiqueRepository;
	}

	@Override
	public Caracteristique createCaracteristique(Caracteristique caracteristique) {

		return caracteristique;
	}

	public List<Caracteristique> getCaracteristiqueList(){
		return caracteristiqueRepository.list();
	}

	@Override
	public Caracteristique getCaracteristiqueById(int id) {
		return null;
	}

}
