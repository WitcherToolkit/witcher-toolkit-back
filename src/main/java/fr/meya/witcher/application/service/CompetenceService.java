package fr.meya.witcher.application.service;

import fr.meya.witcher.domain.model.persistent.Competence;
import fr.meya.witcher.domain.port.in.ICompetenceService;
import fr.meya.witcher.exeption.WitcherToolkitExeption;
import fr.meya.witcher.infrastructure.adapter.out.ICompetenceRepository;
import fr.meya.witcher.message.response.CompetenceVolatile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetenceService implements ICompetenceService {

	private final ICompetenceRepository competenceRepository;

	public CompetenceService(ICompetenceRepository competenceRepository) {
		this.competenceRepository = competenceRepository;
	}

	@Override
	public boolean isValid(CompetenceVolatile competenceVolatile) {
		if (competenceVolatile == null) {
			throw new WitcherToolkitExeption("Aucune competence fournie.");
		}

		if (competenceVolatile.getNom() == null || competenceVolatile.getNom().isBlank()) {
			throw new WitcherToolkitExeption("Le nom de la competence est obligatoire.");
		}

		if (competenceVolatile.getCodeCaracteristique() == null || competenceVolatile.getCodeCaracteristique().isBlank()) {
			throw new WitcherToolkitExeption("Le code de la caractéristique de la competence est obligatoire.");
		}

		if (competenceVolatile.getDescription() == null || competenceVolatile.getDescription().isBlank()) {
			throw new WitcherToolkitExeption("Le description de la competence est obligatoire.");
		}

		if (competenceVolatile.getDescriptionBase10() == null || competenceVolatile.getDescriptionBase10().isBlank()) {
			throw new WitcherToolkitExeption("Le description base 10 de la competence est obligatoire.");
		}

		if (competenceVolatile.getDescriptionBase13() == null || competenceVolatile.getDescriptionBase13().isBlank()) {
			throw new WitcherToolkitExeption("Le description base 13 de la competence est obligatoire.");
		}

		if (competenceVolatile.getDescriptionBase16() == null || competenceVolatile.getDescriptionBase16().isBlank()) {
			throw new WitcherToolkitExeption("Le description base 16 de la competence est obligatoire.");
		}

		if (competenceVolatile.getDescriptionBase20() == null || competenceVolatile.getDescriptionBase20().isBlank()) {
			throw new WitcherToolkitExeption("Le description base 20 de la competence est obligatoire.");
		}

		// Si toutes les vérifications passent
		return true;
	}

	@Override
	public Competence createCompetence(CompetenceVolatile competenceVolatile) {
		// Valider l'objet CaracteristiqueVolatile
		if (!isValid(competenceVolatile)) {
			throw new WitcherToolkitExeption("Les informations de la compétence ne sont pas valides.");
		}

		// Mapper CompetenceVolatile vers Competence
		Competence competence = new Competence();
		competence.setNom(competenceVolatile.getNom());
		competence.setCodeCaracteristique(competenceVolatile.getCodeCaracteristique());
		competence.setDescription(competenceVolatile.getDescription());
		competence.setDescriptionBase10(competenceVolatile.getDescriptionBase10());
		competence.setDescriptionBase13(competenceVolatile.getDescriptionBase13());
		competence.setDescriptionBase16(competenceVolatile.getDescriptionBase16());
		competence.setDescriptionBase20(competenceVolatile.getDescriptionBase20());

		// Sauvegarder l'entité dans la base de données
		return competenceRepository.save(competence);
	}

	@Override
	public Competence getCompetence(Long idCompetence) {
		if (idCompetence == null) {
			throw new WitcherToolkitExeption("L'ID de la competence est null.");
		}

		return competenceRepository.findById(idCompetence).orElseThrow(() -> new WitcherToolkitExeption("La competence avec l'ID " + idCompetence + " n'existe pas."));
	}

	@Override
	public Competence updateCompetence(Long idCompetence, CompetenceVolatile competenceVolatile) {
		if (idCompetence == null) {
			throw new WitcherToolkitExeption("L'ID de la competence est null.");
		}
		if (competenceVolatile == null) {
			throw new WitcherToolkitExeption("Les données de mise à jour sont nulles.");
		}

		Competence competenceExistant = getCompetence(idCompetence);

		if (competenceVolatile.getNom() != null) {
			competenceExistant.setNom(competenceVolatile.getNom());
		}
		if (competenceVolatile.getCodeCaracteristique() != null) {
			competenceExistant.setCodeCaracteristique(competenceVolatile.getCodeCaracteristique());
		}
		if (competenceVolatile.getDescription() != null) {
			competenceExistant.setDescription(competenceVolatile.getDescription());
		}
		if (competenceVolatile.getDescriptionBase10() != null) {
			competenceExistant.setDescriptionBase10(competenceVolatile.getDescriptionBase10());
		}
		if (competenceVolatile.getDescriptionBase13() != null) {
			competenceExistant.setDescriptionBase13(competenceVolatile.getDescriptionBase13());
		}
		if (competenceVolatile.getDescriptionBase16() != null) {
			competenceExistant.setDescriptionBase16(competenceVolatile.getDescriptionBase16());
		}
		if (competenceVolatile.getDescriptionBase20() != null) {
			competenceExistant.setDescriptionBase20(competenceVolatile.getDescriptionBase20());
		}

		return competenceRepository.save(competenceExistant);
	}


	@Override
	public Competence deleteCompetence(Long idCompetence) {
		Competence competenceExistant = getCompetence(idCompetence);
		competenceRepository.delete(competenceExistant);
		return competenceExistant;
	}

	@Override
	public List<Competence> getCompetenceList() {
		return competenceRepository.findAll();
	}

}
