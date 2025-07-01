package fr.meya.witcher.application.service;

import fr.meya.witcher.application.mapper.CaracteristiqueMapper;
import fr.meya.witcher.common.utils.ObjectUtils;
import fr.meya.witcher.common.utils.ValidationRule;
import fr.meya.witcher.common.utils.ValidationUtils;
import fr.meya.witcher.domain.model.persistent.Caracteristique;
import fr.meya.witcher.domain.port.in.ICaracteristiqueService;
import fr.meya.witcher.exeption.WitcherToolkitExeption;
import fr.meya.witcher.infrastructure.adapter.out.ICaracteristiqueRepository;
import fr.meya.witcher.message.response.CaracteristiqueVolatile;
import org.springframework.beans.BeanUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

		Map<String, ValidationRule> fieldRules = Map.of(
		"nom", new ValidationRule("error.caracteristique.nom.required"),
		"code", new ValidationRule("error.caracteristique.code.required"),
		"description", new ValidationRule("error.caracteristique.description.required")
		);

		validationUtils.validateWithRules(caracteristiqueVolatile, fieldRules);

		// Si toutes les vérifications passent
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

		Caracteristique caracteristiqueExistant = caracteristiqueRepository.findById(idCaracteristique)
				.orElseThrow(() -> new WitcherToolkitExeption("Caractéristique non trouvée"));

		BeanUtils.copyProperties(caracteristiqueVolatile, caracteristiqueExistant, ObjectUtils.getNullPropertyNames(caracteristiqueVolatile));

		return caracteristiqueRepository.save(caracteristiqueExistant);
	}

	@Override
	public void deleteCaracteristique(Long idCaracteristique) {
		Caracteristique caracteristiqueExistant = getCaracteristique(idCaracteristique);
		caracteristiqueRepository.delete(caracteristiqueExistant);
	}

}
