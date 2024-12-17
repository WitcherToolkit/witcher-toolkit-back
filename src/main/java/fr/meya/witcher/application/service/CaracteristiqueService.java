package fr.meya.witcher.application.service;

import fr.meya.witcher.domain.port.in.ICaracteristiqueService;
import fr.meya.witcher.exeption.WitcherToolkitExeption;
import fr.meya.witcher.message.response.CaracteristiqueVolatile;
import fr.meya.witcher.domain.model.persistent.Caracteristique;
import fr.meya.witcher.infrastructure.adapter.out.ICaracteristiqueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaracteristiqueService implements ICaracteristiqueService {

	private final ICaracteristiqueRepository caracteristiqueRepository;

	public CaracteristiqueService(ICaracteristiqueRepository caracteristiqueRepository) {
		this.caracteristiqueRepository = caracteristiqueRepository;
	}

	public boolean isValid(CaracteristiqueVolatile caracteristiqueVolatile) {
		if (caracteristiqueVolatile == null) {
			throw new WitcherToolkitExeption("Aucune caractéristique fournie.");
		}

		// Vérification du champ 'nom'
		if (caracteristiqueVolatile.getNom() == null || caracteristiqueVolatile.getNom().isBlank()) {
			throw new WitcherToolkitExeption("Le nom de la caractéristique est obligatoire.");
		}
		if (caracteristiqueVolatile.getNom().length() > 16) {
			throw new WitcherToolkitExeption("Le nom de la caractéristique ne peut pas dépasser 16 caractères.");
		}

		// Vérification du champ 'code'
		if (caracteristiqueVolatile.getCode() == null || caracteristiqueVolatile.getCode().isBlank()) {
			throw new WitcherToolkitExeption("Le code de la caractéristique est obligatoire.");
		}
		if (caracteristiqueVolatile.getCode().length() > 6) {
			throw new WitcherToolkitExeption("Le code de la caractéristique ne peut pas dépasser 6 caractères.");
		}

		// Vérification du champ 'description'
		if (caracteristiqueVolatile.getDescription() == null || caracteristiqueVolatile.getDescription().isBlank()) {
			throw new WitcherToolkitExeption("La description de la caractéristique est obligatoire.");
		}

		// Si toutes les vérifications passent
		return true;
	}

	@Override
	public Caracteristique createCaracteristique(CaracteristiqueVolatile caracteristiqueVolatile) {
		// Valider l'objet CaracteristiqueVolatile
		if (!isValid(caracteristiqueVolatile)) {
			throw new WitcherToolkitExeption("Les informations de la caractéristique ne sont pas valides.");
		}

		// Mapper CaracteristiqueVolatile vers Caracteristique
		Caracteristique caracteristique = new Caracteristique();
		caracteristique.setNom(caracteristiqueVolatile.getNom());
		caracteristique.setCode(caracteristiqueVolatile.getCode());
		caracteristique.setDescription(caracteristiqueVolatile.getDescription());

		// Sauvegarder l'entité dans la base de données
		return caracteristiqueRepository.save(caracteristique);
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
