package fr.meya.witcher.service;

<<<<<<< HEAD:src/main/java/fr/meya/witcher/service/CaracteristiqueService.java
import fr.meya.witcher.model.persistent.Caracteristique;
import fr.meya.witcher.adapter.port.out.CaracteristiqueRepository;
=======
import fr.meya.witcher_toolkit_back.model.persistent.Caracteristique;
import fr.meya.witcher_toolkit_back.port.out.ICaracteristiqueRepository;
>>>>>>> 0769ef2662738535d87b40a1d5bd0e434b6141bf:src/main/java/fr/meya/witcher_toolkit_back/service/CaracteristiqueService.java
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
