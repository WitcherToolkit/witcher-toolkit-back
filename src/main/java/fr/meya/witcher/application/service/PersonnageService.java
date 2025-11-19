package fr.meya.witcher.application.service;

import fr.meya.witcher.application.mapper.PersonnageMapper;
import fr.meya.witcher.common.utils.ObjectUtils;
import fr.meya.witcher.common.utils.ValidationUtils;
import fr.meya.witcher.domain.model.key.CaracteristiquePersonnageId;
import fr.meya.witcher.domain.model.key.CompetencePersonnageId;
import fr.meya.witcher.domain.model.key.EnvoutementPersonnageId;
import fr.meya.witcher.domain.model.key.RituelPersonnageId;
import fr.meya.witcher.domain.model.persistent.*;
import fr.meya.witcher.domain.port.in.IPersonnageService;
import fr.meya.witcher.exeption.WitcherToolkitExeption;
import fr.meya.witcher.infrastructure.adapter.out.*;
import fr.meya.witcher.message.response.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class PersonnageService implements IPersonnageService {

    private final IPersonnageRepository personnageRepository;
    private final IRaceRepository raceRepository;
    private final IProfessionRepository professionRepository;
    private final ICompetenceRepository competenceRepository;
    private final ICaracteristiqueRepository  caracteristiqueRepository;
    private final IRituelRepository rituelRepository;
    private final IEnvoutementRepository envoutementRepository;

    private final PersonnageMapper personnageMapper;
    private final ValidationUtils validationUtils;

    public PersonnageService(
            IPersonnageRepository personnageRepository,
            PersonnageMapper personnageMapper,
            MessageSource messageSource,
            IRaceRepository raceRepository,
            IProfessionRepository professionRepository,
            ICompetenceRepository competenceRepository,
            ICaracteristiqueRepository  caracteristiqueRepository,
            IRituelRepository rituelRepository,
            IEnvoutementRepository envoutementRepository
    ) {
        this.personnageRepository = personnageRepository;
        this.raceRepository = raceRepository;
        this.professionRepository = professionRepository;
        this.competenceRepository = competenceRepository;
        this.caracteristiqueRepository = caracteristiqueRepository;
        this.rituelRepository = rituelRepository;
        this.envoutementRepository = envoutementRepository;
        this.personnageMapper = personnageMapper;
        this.validationUtils = new ValidationUtils(messageSource);
    }

    @Override
    public boolean isValid(PersonnageVolatile personnageVolatile) {
        if (personnageVolatile == null) {
            throw new WitcherToolkitExeption("error.personnage.null");
        }
        return true;
    }

    @Override
    public List<PersonnageVolatile> getPersonnageList() {
        return personnageRepository.findAll().stream()
                .map(personnageMapper::toPersonnageDto)
                .toList();
    }

    @Override
    public PersonnageVolatile getPersonnage(UUID idPersonnage) {  // ← Changé le type de retour
        log.info("Récupération du personnage avec l'ID : {}", idPersonnage);

        if (idPersonnage == null) {
            throw new WitcherToolkitExeption("L'ID du personnage est null.");
        }

        Personnage personnage = personnageRepository.findById(idPersonnage)
                .orElseThrow(() -> new WitcherToolkitExeption("Personnage avec l'ID " + idPersonnage + " non trouvé."));

        return personnageMapper.toPersonnageDto(personnage);  // ← Retourne le DTO
    }

    @Override
    public PersonnageVolatile createPersonnage(PersonnageVolatile personnageVolatile) {
        isValid(personnageVolatile);

        // 1. Mapper DTO → Entité
        Personnage personnage = personnageMapper.toPersonnageEntity(personnageVolatile);

        // Charger Race et Profession
        if (personnageVolatile.getRace() != null && personnageVolatile.getRace().getIdRace() != null) {
            personnage.setRace(raceRepository.getReferenceById(personnageVolatile.getRace().getIdRace()));
        }

        if (personnageVolatile.getProfession() != null && personnageVolatile.getProfession().getIdProfession() != null) {
            personnage.setProfession(professionRepository.getReferenceById(personnageVolatile.getProfession().getIdProfession()));
        }

        // 2. Configurer les relations
        // CompetencePersonnage
        if (personnage.getCompetencePersonnageList() != null && personnageVolatile.getCompetencePersonnageList() != null) {
            for (int i = 0; i < personnage.getCompetencePersonnageList().size(); i++) {
                CompetencePersonnage comp = personnage.getCompetencePersonnageList().get(i);
                CompetencePersonnageVolatile dtoComp = personnageVolatile.getCompetencePersonnageList().get(i);

                if (dtoComp.getCompetence() != null && dtoComp.getCompetence().getIdCompetence() != null) {
                    UUID idComp = dtoComp.getCompetence().getIdCompetence();
                    comp.setCompetence(competenceRepository.getReferenceById(idComp));

                    CompetencePersonnageId id = new CompetencePersonnageId();
                    id.setIdCompetence(idComp);
                    id.setIdPersonnage(null);
                    comp.setId(id);
                    comp.setPersonnage(personnage);
                }
            }
        }

        // CaracteristiquePersonnage
        if (personnage.getCaracteristiquePersonnageList() != null && personnageVolatile.getCaracteristiquePersonnageList() != null) {
            for (int i = 0; i < personnage.getCaracteristiquePersonnageList().size(); i++) {
                CaracteristiquePersonnage carac = personnage.getCaracteristiquePersonnageList().get(i);
                CaracteristiquePersonnageVolatile dtoCarac = personnageVolatile.getCaracteristiquePersonnageList().get(i);

                if (dtoCarac.getCaracteristique() != null && dtoCarac.getCaracteristique().getIdCaracteristique() != null) {
                    UUID idCarac = dtoCarac.getCaracteristique().getIdCaracteristique();
                    carac.setCaracteristique(caracteristiqueRepository.getReferenceById(idCarac));

                    CaracteristiquePersonnageId id = new CaracteristiquePersonnageId();
                    id.setIdCaracteristique(idCarac);
                    id.setIdPersonnage(null);
                    carac.setId(id);
                    carac.setPersonnage(personnage);
                }
            }
        }

        // Inventaire
        if (personnage.getInventaireList() != null) {
            personnage.getInventaireList().forEach(inv -> inv.setPersonnage(personnage));
        }

        // RituelPersonnage
        if (personnageVolatile.getRituelList() != null && !personnageVolatile.getRituelList().isEmpty()) {
            List<RituelPersonnage> rituelPersonnages = new ArrayList<>();

            for (RituelVolatile dtoRituel : personnageVolatile.getRituelList()) {
                if (dtoRituel.getIdRituel() != null) {
                    RituelPersonnage rituelPerso = new RituelPersonnage();
                    rituelPerso.setRituel(rituelRepository.getReferenceById(dtoRituel.getIdRituel()));
                    rituelPerso.setPersonnage(personnage);

                    RituelPersonnageId id = new RituelPersonnageId();
                    id.setIdRituel(dtoRituel.getIdRituel());
                    id.setIdPersonnage(null);
                    rituelPerso.setIdRituelPersonnage(id);

                    rituelPersonnages.add(rituelPerso);
                }
            }

            personnage.setRituelPersonnageList(rituelPersonnages);
        }

        // EnvoutementPersonnage
        if (personnageVolatile.getEnvoutementList() != null && !personnageVolatile.getEnvoutementList().isEmpty()) {
            List<EnvoutementPersonnage> envoutementPersonnages = new ArrayList<>();

            for (EnvoutementVolatile dtoEnv : personnageVolatile.getEnvoutementList()) {
                if (dtoEnv.getIdEnvoutement() != null) {
                    EnvoutementPersonnage envPerso = new EnvoutementPersonnage();
                    envPerso.setEnvoutement(envoutementRepository.getReferenceById(dtoEnv.getIdEnvoutement()));
                    envPerso.setPersonnage(personnage);

                    EnvoutementPersonnageId id = new EnvoutementPersonnageId();
                    id.setIdEnvoutement(dtoEnv.getIdEnvoutement());
                    id.setIdPersonnage(null);
                    envPerso.setId(id);

                    envoutementPersonnages.add(envPerso);
                }
            }

            personnage.setEnvoutementPersonnageList(envoutementPersonnages);
        }

        // 3. Sauvegarder L'ENTITÉ en base de données
        Personnage saved = personnageRepository.save(personnage);

        // 4. Convertir L'ENTITÉ SAUVEGARDÉE → DTO pour le retour au front
        return personnageMapper.toPersonnageDto(saved);
    }

    @Override
    public PersonnageVolatile updatePersonnage(UUID idPersonnage, PersonnageVolatile personnageVolatile) {  // ← Changé le type de retour
        log.info("Début de la mise à jour du personnage - ID : {} - Données : {}", idPersonnage, personnageVolatile);
        isValid(personnageVolatile);

        // 1. Récupérer l'entité existante
        if (idPersonnage == null) {
            throw new WitcherToolkitExeption("L'ID du personnage est null.");
        }

        Personnage existing = personnageRepository.findById(idPersonnage)
                .orElseThrow(() -> new WitcherToolkitExeption("Personnage avec l'ID " + idPersonnage + " non trouvé."));

        // 2. Copier les propriétés non-null du DTO vers l'entité existante
        BeanUtils.copyProperties(personnageVolatile, existing, ObjectUtils.getNullPropertyNames(personnageVolatile));

        // 3. Sauvegarder
        Personnage saved = personnageRepository.save(existing);

        log.info("Personnage mis à jour - ID : {}", saved.getIdPersonnage());

        // 4. Retourner le DTO
        return personnageMapper.toPersonnageDto(saved);  // ← Retourne le DTO
    }

    @Override
    public void deletePersonnage(UUID idPersonnage) {
        if (idPersonnage == null) {
            throw new WitcherToolkitExeption("L'ID du personnage est null.");
        }

        Personnage personnage = personnageRepository.findById(idPersonnage)
                .orElseThrow(() -> new WitcherToolkitExeption("Personnage avec l'ID " + idPersonnage + " non trouvé."));

        personnageRepository.delete(personnage);
        log.info("Personnage supprimé - ID : {}", idPersonnage);
    }
}