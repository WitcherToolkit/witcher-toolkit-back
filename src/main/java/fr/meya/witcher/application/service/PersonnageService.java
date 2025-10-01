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
        // Tu peux ajouter d'autres règles ici
        return true;
    }

    @Override
    public List<PersonnageVolatile> getPersonnageList() {
        return personnageRepository.findAll().stream()
                .map(personnageMapper::toPersonnageDto)
                .toList();
    }

    @Override
    public Personnage getPersonnage(Long idPersonnage) {
        if (idPersonnage == null) {
            throw new WitcherToolkitExeption("L'ID du personnage est null.");
        }
        return personnageRepository.findById(idPersonnage)
                .orElseThrow(() -> new WitcherToolkitExeption("Personnage avec l'ID " + idPersonnage + " non trouvé."));
    }

    @Override
    public Personnage createPersonnage(PersonnageVolatile personnageVolatile) {
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
                    Long idComp = dtoComp.getCompetence().getIdCompetence();
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
                    Long idCarac = dtoCarac.getCaracteristique().getIdCaracteristique();
                    carac.setCaracteristique(caracteristiqueRepository.getReferenceById(idCarac));

                    CaracteristiquePersonnageId id = new CaracteristiquePersonnageId();
                    id.setIdCaracteristique(idCarac);
                    id.setIdPersonnage(null);
                    carac.setId(id);
                    carac.setPersonnage(personnage);
                }
            }
        }

        // Inventaire (pas de clé composite)
        if (personnage.getInventaireList() != null) {
            personnage.getInventaireList().forEach(inv -> inv.setPersonnage(personnage));
        }

        // RituelPersonnage - Créer les entités de liaison manuellement
        if (personnageVolatile.getRituelList() != null && !personnageVolatile.getRituelList().isEmpty()) {
            List<RituelPersonnage> rituelPersonnages = new ArrayList<>();

            for (RituelVolatile dtoRituel : personnageVolatile.getRituelList()) {
                if (dtoRituel.getIdRituel() != null) {
                    RituelPersonnage rituelPerso = new RituelPersonnage();
                    rituelPerso.setRituel(rituelRepository.getReferenceById(dtoRituel.getIdRituel()));
                    rituelPerso.setPersonnage(personnage);

                    RituelPersonnageId id = new RituelPersonnageId();
                    id.setIdRituel(dtoRituel.getIdRituel());
                    id.setIdPersonnage(null); // Sera rempli par @MapsId
                    rituelPerso.setIdRituelPersonnage(id);

                    rituelPersonnages.add(rituelPerso);
                }
            }

            personnage.setRituelPersonnageList(rituelPersonnages);
        }

        // EnvoutementPersonnage - Créer les entités de liaison manuellement
        if (personnageVolatile.getEnvoutementList() != null && !personnageVolatile.getEnvoutementList().isEmpty()) {
            List<EnvoutementPersonnage> envoutementPersonnages = new ArrayList<>();

            for (EnvoutementVolatile dtoEnv : personnageVolatile.getEnvoutementList()) {
                if (dtoEnv.getIdEnvoutement() != null) {
                    EnvoutementPersonnage envPerso = new EnvoutementPersonnage();
                    envPerso.setEnvoutement(envoutementRepository.getReferenceById(dtoEnv.getIdEnvoutement()));
                    envPerso.setPersonnage(personnage);

                    EnvoutementPersonnageId id = new EnvoutementPersonnageId();
                    id.setIdEnvoutement(dtoEnv.getIdEnvoutement());
                    id.setIdPersonnage(null); // Sera rempli par @MapsId
                    envPerso.setId(id);

                    envoutementPersonnages.add(envPerso);
                }
            }

            personnage.setEnvoutementPersonnageList(envoutementPersonnages);
        }

        return personnageRepository.save(personnage);
    }

    @Override
    public Personnage updatePersonnage(Long idPersonnage, PersonnageVolatile personnageVolatile) {
        log.info("Début de la mise à jour du personnage - ID : {} - Données : {}", idPersonnage, personnageVolatile);
        isValid(personnageVolatile);

        Personnage existing = getPersonnage(idPersonnage);
        BeanUtils.copyProperties(personnageVolatile, existing, ObjectUtils.getNullPropertyNames(personnageVolatile));

        Personnage saved = personnageRepository.save(existing);
        log.info("Personnage mis à jour - ID : {}", saved.getIdPersonnage());
        return saved;
    }

    @Override
    public void deletePersonnage(Long idPersonnage) {
        Personnage personnage = getPersonnage(idPersonnage);
        personnageRepository.delete(personnage);
    }
}
