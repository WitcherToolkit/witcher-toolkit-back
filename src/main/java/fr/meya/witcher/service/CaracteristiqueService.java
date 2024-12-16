package fr.meya.witcher.service;

import fr.meya.witcher.model.persistent.Caracteristique;
import fr.meya.witcher.adapter.port.out.ICaracteristiqueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaracteristiqueService implements ICaracteristiqueService{

	private final ICaracteristiqueRepository caracteristiqueRepository;

	public CaracteristiqueService(ICaracteristiqueRepository caracteristiqueRepository) {
		this.caracteristiqueRepository = caracteristiqueRepository;
	}

	@Override
	public List<Caracteristique> getCaracteristiqueList(){
		return caracteristiqueRepository.findAll();
	}

}
