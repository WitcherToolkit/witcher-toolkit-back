package fr.meya.witcher.application.service;

import fr.meya.witcher.application.mapper.CompetenceMapper;
import fr.meya.witcher.common.utils.ObjectUtils;
import fr.meya.witcher.common.utils.ValidationRule;
import fr.meya.witcher.common.utils.ValidationUtils;
import fr.meya.witcher.domain.model.persistent.Competence;
import fr.meya.witcher.domain.port.in.ICompetenceService;
import fr.meya.witcher.exeption.WitcherToolkitExeption;
import fr.meya.witcher.infrastructure.adapter.out.ICompetenceRepository;
import fr.meya.witcher.message.response.CompetenceVolatile;
import org.springframework.beans.BeanUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CompetenceService implements ICompetenceService {

	private final ICompetenceRepository iCompetenceRepository;
	private final CompetenceMapper competenceMapper;
	private final ValidationUtils validationUtils;

	public CompetenceService(ICompetenceRepository iCompetenceRepository, CompetenceMapper competenceMapper, MessageSource messageSource) {
		this.iCompetenceRepository = iCompetenceRepository;
		this.competenceMapper = competenceMapper;
		this.validationUtils = new ValidationUtils(messageSource);
	}

	@Override
	public boolean isValid(CompetenceVolatile competenceVolatile) {
		if (competenceVolatile == null) {
			throw new WitcherToolkitExeption("error.competence.null");
		}

		Map<String, ValidationRule> fieldRules = Map.of(
				"nom", new ValidationRule("error.competence.nom.required"),
				"codeCaracteristique", new ValidationRule("error.competence.codeCaracteristique.required"),
				"description", new ValidationRule("error.competence.description.required"),
				"description10", new ValidationRule("error.competence.description10.required"),
				"description13", new ValidationRule("error.competence.description13.required"),
				"description16", new ValidationRule("error.competence.description16.required"),
				"description20", new ValidationRule("error.competence.description20.required")
		);

		validationUtils.validateWithRules(competenceVolatile, fieldRules);

		// Si toutes les vérifications passent
		return true;
	}

	@Override
	public List<CompetenceVolatile> getCompetenceList() {
		return iCompetenceRepository.findAll().stream().map(competenceMapper::toCompetenceDto).toList();
	}

	@Override
	public Competence getCompetence(Long idCompetence) {
		if (idCompetence == null) {
			throw new WitcherToolkitExeption("L'ID de la competence est null.");
		}

		return iCompetenceRepository.findById(idCompetence).orElseThrow(() -> new WitcherToolkitExeption("La competence avec l'ID " + idCompetence + " n'existe pas."));
	}

	@Override
	public Competence createCompetence(CompetenceVolatile competenceVolatile) {
		// Valider l'objet CaracteristiqueVolatile
		isValid(competenceVolatile);

		// Mapper CompetenceVolatile vers Competence
		Competence competence =competenceMapper.toCompetenceEntity(competenceVolatile);

		// Sauvegarder l'entité dans la base de données
		return iCompetenceRepository.save(competence);
	}

	@Override
	public Competence updateCompetence(Long idCompetence, CompetenceVolatile competenceVolatile) {

		Competence competenceExistant = iCompetenceRepository.findById(idCompetence)
				.orElseThrow(() -> new WitcherToolkitExeption("Compétence non trouvée"));

		BeanUtils.copyProperties(competenceVolatile, competenceExistant, ObjectUtils.getNullPropertyNames(competenceVolatile));

		return iCompetenceRepository.save(competenceExistant);
	}


	@Override
	public void deleteCompetence(Long idCompetence) {
		Competence competenceExistant = getCompetence(idCompetence);
		iCompetenceRepository.delete(competenceExistant);
	}

}
