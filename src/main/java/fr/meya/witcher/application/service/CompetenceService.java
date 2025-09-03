package fr.meya.witcher.application.service;

import fr.meya.witcher.application.mapper.CompetenceMapper;
import fr.meya.witcher.common.utils.ObjectUtils;
import fr.meya.witcher.common.utils.ValidationRule;
import fr.meya.witcher.common.utils.ValidationUtils;
import fr.meya.witcher.domain.model.persistent.Caracteristique;
import fr.meya.witcher.domain.model.persistent.Competence;
import fr.meya.witcher.domain.port.in.ICompetenceService;
import fr.meya.witcher.exeption.WitcherToolkitExeption;
import fr.meya.witcher.infrastructure.adapter.out.ICompetenceRepository;
import fr.meya.witcher.message.response.CompetenceVolatile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class CompetenceService implements ICompetenceService {

	private final ICompetenceRepository competenceRepository;
	private final CompetenceMapper competenceMapper;
	private final ValidationUtils validationUtils;

	public CompetenceService(ICompetenceRepository iCompetenceRepository, CompetenceMapper competenceMapper, MessageSource messageSource) {
		this.competenceRepository = iCompetenceRepository;
		this.competenceMapper = competenceMapper;
		this.validationUtils = new ValidationUtils(messageSource);
	}

	@Override
	public boolean isValid(CompetenceVolatile competenceVolatile) {
		if (competenceVolatile == null) {
			throw new WitcherToolkitExeption("error.competence.null");
		}

		// Si toutes les vérifications passent
		return true;
	}

	@Override
	public List<CompetenceVolatile> getCompetenceList() {
		return competenceRepository.findAllByOrderByNomAsc().stream().map(competenceMapper::toCompetenceDto).toList();
	}

	@Override
	public Competence getCompetence(Long idCompetence) {
		if (idCompetence == null) {
			throw new WitcherToolkitExeption("L'ID de la competence est null.");
		}

		return competenceRepository.findById(idCompetence).orElseThrow(() -> new WitcherToolkitExeption("La competence avec l'ID " + idCompetence + " n'existe pas."));
	}

	@Override
	public Competence createCompetence(CompetenceVolatile competenceVolatile) {
		// Valider l'objet CaracteristiqueVolatile
		isValid(competenceVolatile);

		// Mapper CompetenceVolatile vers Competence
		Competence competence =competenceMapper.toCompetenceEntity(competenceVolatile);

		// Sauvegarder l'entité dans la base de données
		return competenceRepository.save(competence);
	}

	@Override
	public Competence updateCompetence(Long idCompetence, CompetenceVolatile competenceVolatile) {
		log.info("Début de la méthode updateCompetence - ID : {} - Données reçues : {}", idCompetence, competenceVolatile);

		isValid(competenceVolatile);
		log.info("Validation des données réussie");

		Competence competenceExistant = competenceRepository.findById(idCompetence)
				.orElseThrow(() -> new WitcherToolkitExeption("Competence non trouvée"));
		log.info("Competence existante trouvée - Nom: {}, Description: {}, exclusive: {}",
				competenceExistant.getNom(),
				competenceExistant.getDescription(),
				competenceExistant.isExclusive());
		;

		log.info("Avant copyProperties - Nom: {}, Description: {}, exclusive: {}",
				competenceExistant.getNom(),
				competenceExistant.getDescription(),
				competenceExistant.isExclusive());

		BeanUtils.copyProperties(competenceVolatile, competenceExistant, ObjectUtils.getNullPropertyNames(competenceVolatile));

		log.info("Après copyProperties - Nom: {}, Description: {}, exclusive: {}",
				competenceExistant.getNom(),
				competenceExistant.getDescription(),
				competenceExistant.isExclusive());

		Competence competenceSauvegardee = competenceRepository.save(competenceExistant);
		log.info("Après sauvegarde - Nom: {}, Description: {}, exclusive: {}",
				competenceSauvegardee.getNom(),
				competenceSauvegardee.getDescription(),
				competenceSauvegardee.isExclusive());

		return competenceSauvegardee;
	}


	@Override
	public void deleteCompetence(Long idCompetence) {
		Competence competenceExistant = getCompetence(idCompetence);
		competenceRepository.delete(competenceExistant);
	}

}
