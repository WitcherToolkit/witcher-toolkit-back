package fr.meya.witcher.service;

import fr.meya.witcher.exeption.WitcherToolkitExeption;
import fr.meya.witcher.model.message.CaracteristiqueVolatile;
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
	public Caracteristique createCaracteristique(Caracteristique caracteristique) {
		caracteristiqueRepository.save(caracteristique);
		return caracteristique;
	}

	@Override
	public Caracteristique getCaracteristique(Long idCaracteristique) {

		if (idCaracteristique == null) {
			throw new WitcherToolkitExeption("L'ID de la caractéristique est null.");
		}

		return caracteristiqueRepository.findById(idCaracteristique).orElseThrow(() -> new WitcherToolkitExeption("La caractéristique avec l'ID " + idCaracteristique + " n'existe pas."));
	}

	@Override
	public Caracteristique updateCaracteristique(Long idCaracteristique, CaracteristiqueVolatile caracteristiqueVolatile) {

		if (idCaracteristique == null) {
			throw new WitcherToolkitExeption("L'ID de la caractéristique est null.");
		}

		if (caracteristiqueVolatile == null) {
			throw new WitcherToolkitExeption("Les données de mise à jour sont nulles.");
		}

		Caracteristique caracteristiqueExistant = getCaracteristique(idCaracteristique);

		// TODO - trouver une meilleure façon de faire.
		if (caracteristiqueVolatile.getNom() != null) {
			caracteristiqueExistant.setNom(caracteristiqueVolatile.getNom());
		}

		if (caracteristiqueVolatile.getCode() != null) {
			caracteristiqueExistant.setCode(caracteristiqueVolatile.getCode());
		}

		if (caracteristiqueVolatile.getDescription() != null) {
			caracteristiqueExistant.setDescription(caracteristiqueVolatile.getDescription());
		}

		return caracteristiqueRepository.save(caracteristiqueExistant);

	}

	@Override
	public Caracteristique deleteCaracteristique(Long idCaracteristique) {
		Caracteristique caracteristiqueExistant = getCaracteristique(idCaracteristique);
		caracteristiqueRepository.delete(caracteristiqueExistant);
		return caracteristiqueExistant;
	}

	@Override
	public List<Caracteristique> getCaracteristiqueList(){
		return caracteristiqueRepository.findAll();
	}

}
