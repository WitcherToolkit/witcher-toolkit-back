package fr.meya.witcher.application.service;

import fr.meya.witcher.application.mapper.CaracteristiqueMapper;
import fr.meya.witcher.common.utils.ObjectUtils;
import fr.meya.witcher.common.utils.ValidationRule;
import fr.meya.witcher.common.utils.ValidationUtils;
import fr.meya.witcher.domain.model.persistent.Caracteristique;
import fr.meya.witcher.domain.model.persistent.Magie;
import fr.meya.witcher.domain.port.in.ICaracteristiqueService;
import fr.meya.witcher.exeption.WitcherToolkitExeption;
import fr.meya.witcher.infrastructure.adapter.out.ICaracteristiqueRepository;
import fr.meya.witcher.message.response.CaracteristiqueVolatile;
import fr.meya.witcher.message.response.MagieVolatile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class CaracteristiqueService implements ICaracteristiqueService {

	private final ICaracteristiqueRepository caracteristiqueRepository;
	private final CaracteristiqueMapper caracteristiqueMapper;
	private final ValidationUtils validationUtils;

	public CaracteristiqueService(ICaracteristiqueRepository caracteristiqueRepository, CaracteristiqueMapper caracteristiqueMapper, MessageSource messageSource) {
		this.caracteristiqueRepository = caracteristiqueRepository;
		this.caracteristiqueMapper = caracteristiqueMapper;
		this.validationUtils = new ValidationUtils(messageSource);
	}

	@Override
	public boolean isValid(CaracteristiqueVolatile caracteristiqueVolatile) {
		if (caracteristiqueVolatile == null) {
			throw new WitcherToolkitExeption("error.caracteristique.null");
		}

		return true;
	}

	@Override
	public List<CaracteristiqueVolatile> getCaracteristiqueList(){
		return caracteristiqueRepository.findAll().stream().map(caracteristiqueMapper::toCaracteristiqueDto).toList();
	}

	@Override
	public Caracteristique getCaracteristique(Long idCaracteristique) {
		if (idCaracteristique == null) {
			throw new WitcherToolkitExeption("L'ID de la caractéristique est null.");
		}

		return caracteristiqueRepository.findById(idCaracteristique).orElseThrow(() -> new WitcherToolkitExeption("La caractéristique avec l'ID " + idCaracteristique + " n'existe pas."));
	}

	@Override
	public Caracteristique createCaracteristique(CaracteristiqueVolatile caracteristiqueVolatile) {
		isValid(caracteristiqueVolatile);

		// Mapper CaracteristiqueVolatile vers Caracteristique
		Caracteristique caracteristique = caracteristiqueMapper.toCaracteristiqueEntity(caracteristiqueVolatile);

		// Sauvegarder l'entité dans la base de données
		return caracteristiqueRepository.save(caracteristique);
	}

	@Override
	public Caracteristique updateCaracteristique(Long idCaracteristique, CaracteristiqueVolatile caracteristiqueVolatile) {
		log.info("Début de la méthode updateCaracteristique - ID : {} - Données reçues : {}", idCaracteristique, caracteristiqueVolatile);

		isValid(caracteristiqueVolatile);
		log.info("Validation des données réussie");

		Caracteristique caracteristiqueExistant = caracteristiqueRepository.findById(idCaracteristique)
				.orElseThrow(() -> new WitcherToolkitExeption("Caracteristique non trouvée"));
		log.info("Caracteristique existante trouvée - Nom: {}, Code: {}, Description: {}",
				caracteristiqueExistant.getNom(),
				caracteristiqueExistant.getCode(),
				caracteristiqueExistant.getDescription());

		log.info("Avant copyProperties - Nom: {}, Code: {}, Description: {}",
				caracteristiqueExistant.getNom(),
				caracteristiqueExistant.getCode(),
				caracteristiqueExistant.getDescription());

		BeanUtils.copyProperties(caracteristiqueVolatile, caracteristiqueExistant, ObjectUtils.getNullPropertyNames(caracteristiqueVolatile));

		log.info("Après copyProperties - Nom: {}, Code: {}, Description: {}",
				caracteristiqueExistant.getNom(),
				caracteristiqueExistant.getCode(),
				caracteristiqueExistant.getDescription());

		Caracteristique caracteristiqueSauvegardee = caracteristiqueRepository.save(caracteristiqueExistant);
		log.info("Après sauvegarde - Nom: {}, Code: {}, Description: {}",
				caracteristiqueSauvegardee.getNom(),
				caracteristiqueSauvegardee.getCode(),
				caracteristiqueSauvegardee.getDescription());

		return caracteristiqueSauvegardee;
	}

	@Override
	public void deleteCaracteristique(Long idCaracteristique) {
		Caracteristique caracteristiqueExistant = getCaracteristique(idCaracteristique);
		caracteristiqueRepository.delete(caracteristiqueExistant);
	}

}
