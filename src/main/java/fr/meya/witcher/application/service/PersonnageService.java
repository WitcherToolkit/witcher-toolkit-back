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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
    private final IUserRepository userRepository;

    private final PersonnageMapper personnageMapper;
    private final ValidationUtils validationUtils;

    public PersonnageService(
            IPersonnageRepository personnageRepository,
            PersonnageMapper personnageMapper,
            MessageSource messageSource,
            IRaceRepository raceRepository,
            IProfessionRepository professionRepository,
            ICompetenceRepository competenceRepository,
            ICaracteristiqueRepository caracteristiqueRepository,
            IRituelRepository rituelRepository,
            IEnvoutementRepository envoutementRepository,
            IUserRepository userRepository
    ) {
        this.personnageRepository = personnageRepository;
        this.raceRepository = raceRepository;
        this.professionRepository = professionRepository;
        this.competenceRepository = competenceRepository;
        this.caracteristiqueRepository = caracteristiqueRepository;
        this.rituelRepository = rituelRepository;
        this.envoutementRepository = envoutementRepository;
        this.userRepository = userRepository;
        this.personnageMapper = personnageMapper;
        this.validationUtils = new ValidationUtils(messageSource);
    }

    /**
     * Récupère l'ID de l'utilisateur actuellement connecté sous forme de String.
     * @return
     */
    private String getCurrentUserIdString() { // Renommé pour plus de clarté
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return ((UserDetails) authentication.getPrincipal()).getUsername(); // Le username est souvent l'ID ou un identifiant unique
        }
        throw new WitcherToolkitExeption("error.user.unauthenticated");
    }

    /**
     * Récupère l'objet User complet de l'utilisateur actuellement connecté.
     * @return
     */
    private User getCurrentUser() {
        String userIdString = getCurrentUserIdString();
        // Utiliser findById sur le UserRepository pour obtenir l'objet User complet
        return userRepository.findById(UUID.fromString(userIdString)) // Assure-toi que ton ID User est un UUID
                .orElseThrow(() -> new WitcherToolkitExeption("error.user.notfound", new Object[]{userIdString}));
    }


    /**
     * Validation basique du DTO PersonnageVolatile.
     */
    @Override
    public boolean isValid(PersonnageVolatile personnageVolatile) {
        if (personnageVolatile == null) {
            throw new WitcherToolkitExeption("error.personnage.null");
        }
        return true;
    }

    /**
     * Récupère la liste de tous les personnages.
     */
    @Override
    public List<PersonnageVolatile> getPersonnageList() {
        // TODO: Filtrer la liste pour ne retourner que les personnages du user connecté !
        log.warn("getPersonnageList() n'est pas encore filtré par utilisateur.");
        // Pour l'instant, on retourne tout, mais il faudrait ajouter la logique de filtrage ici.
        return personnageRepository.findAll().stream()
                .map(personnageMapper::toPersonnageDto)
                .toList();
    }

    /**
     * Récupère un personnage par son ID.
     */
    @Override
    public PersonnageVolatile getPersonnage(UUID idPersonnage) {
        log.info("Récupération du personnage avec l'ID : {}", idPersonnage);

        if (idPersonnage == null) {
            throw new WitcherToolkitExeption("error.personnage.id.null");
        }

        Personnage personnage = personnageRepository.findById(idPersonnage)
                .orElseThrow(() -> new WitcherToolkitExeption("error.personnage.notfound", new Object[]{idPersonnage}));

        // Vérification que le personnage appartient bien à l'utilisateur connecté
        User currentUser = getCurrentUser();
        if (!personnage.getUser().equals(currentUser)) { // Comparaison des objets User
            throw new WitcherToolkitExeption("error.personnage.forbidden");
        }

        return personnageMapper.toPersonnageDto(personnage);
    }

    /**
     * Crée un nouveau personnage.
     */
    @Override
    public PersonnageVolatile createPersonnage(PersonnageVolatile personnageVolatile) {
        isValid(personnageVolatile);

        // 1. Mapper DTO → Entité
        Personnage personnage = personnageMapper.toPersonnageEntity(personnageVolatile);

        // !!! NOUVEAU : Associer l'objet User connecté au personnage !!!
        User currentUser = getCurrentUser(); // Récupère l'objet User connecté
        personnage.setUser(currentUser);      // Associe l'objet User à l'entité Personnage

        // Charger Race et Profession (ton code existant)
        if (personnageVolatile.getRace() != null && personnageVolatile.getRace().getIdRace() != null) {
            personnage.setRace(raceRepository.getReferenceById(personnageVolatile.getRace().getIdRace()));
        }

        if (personnageVolatile.getProfession() != null && personnageVolatile.getProfession().getIdProfession() != null) {
            personnage.setProfession(professionRepository.getReferenceById(personnageVolatile.getProfession().getIdProfession()));
        }

        // 2. Configurer les relations (ton code existant)
        // CompetencePersonnage
        if (personnageVolatile.getCompetencePersonnageList() != null) {
            personnage.setCompetencePersonnageList(personnageVolatile.getCompetencePersonnageList().stream()
                    .map(dtoComp -> {
                        CompetencePersonnage comp = new CompetencePersonnage();
                        if (dtoComp.getCompetence() != null && dtoComp.getCompetence().getIdCompetence() != null) {
                            UUID idComp = dtoComp.getCompetence().getIdCompetence();
                            comp.setCompetence(competenceRepository.getReferenceById(idComp));
                            CompetencePersonnageId id = new CompetencePersonnageId();
                            id.setIdCompetence(idComp);
                            id.setIdPersonnage(null); // Sera défini lors de la sauvegarde si la relation est bien gérée
                            comp.setId(id);
                        }
                        comp.setPersonnage(personnage); // Lien bidirectionnel
                        return comp;
                    })
                    .collect(Collectors.toList()));
        }

        // CaracteristiquePersonnage
        if (personnageVolatile.getCaracteristiquePersonnageList() != null) {
            personnage.setCaracteristiquePersonnageList(personnageVolatile.getCaracteristiquePersonnageList().stream()
                    .map(dtoCarac -> {
                        CaracteristiquePersonnage carac = new CaracteristiquePersonnage();
                        if (dtoCarac.getCaracteristique() != null && dtoCarac.getCaracteristique().getIdCaracteristique() != null) {
                            UUID idCarac = dtoCarac.getCaracteristique().getIdCaracteristique();
                            carac.setCaracteristique(caracteristiqueRepository.getReferenceById(idCarac));
                            CaracteristiquePersonnageId id = new CaracteristiquePersonnageId();
                            id.setIdCaracteristique(idCarac);
                            id.setIdPersonnage(null); // Sera défini lors de la sauvegarde
                            carac.setId(id);
                        }
                        carac.setPersonnage(personnage); // Lien bidirectionnel
                        return carac;
                    })
                    .collect(Collectors.toList()));
        }

        // Inventaire
        if (personnageVolatile.getInventaireList() != null) {
            personnage.getInventaireList().forEach(inv -> inv.setPersonnage(personnage));
        }

        // RituelPersonnage
        if (personnageVolatile.getRituelList() != null && !personnageVolatile.getRituelList().isEmpty()) {
            List<RituelPersonnage> rituelPersonnages = new ArrayList<>();
            for (RituelVolatile dtoRituel : personnageVolatile.getRituelList()) {
                if (dtoRituel.getIdRituel() != null) {
                    RituelPersonnage rituelPerso = new RituelPersonnage();
                    rituelPerso.setRituel(rituelRepository.getReferenceById(dtoRituel.getIdRituel()));
                    rituelPerso.setPersonnage(personnage); // Lien bidirectionnel

                    RituelPersonnageId id = new RituelPersonnageId();
                    id.setIdRituel(dtoRituel.getIdRituel());
                    id.setIdPersonnage(null); // Sera défini lors de la sauvegarde
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
                    envPerso.setPersonnage(personnage); // Lien bidirectionnel

                    EnvoutementPersonnageId id = new EnvoutementPersonnageId();
                    id.setIdEnvoutement(dtoEnv.getIdEnvoutement());
                    id.setIdPersonnage(null); // Sera défini lors de la sauvegarde
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

    /**
     * Met à jour un personnage existant.
     */
    @Override
    public PersonnageVolatile updatePersonnage(UUID idPersonnage, PersonnageVolatile personnageVolatile) {
        log.info("Début de la mise à jour du personnage - ID : {} - Données : {}", idPersonnage, personnageVolatile);
        isValid(personnageVolatile);

        // 1. Récupérer l'entité existante
        if (idPersonnage == null) {
            throw new WitcherToolkitExeption("error.personnage.id.null");
        }

        Personnage existing = personnageRepository.findById(idPersonnage)
                .orElseThrow(() -> new WitcherToolkitExeption("error.personnage.notfound", new Object[]{idPersonnage}));

        // !!! NOUVEAU : Vérifier si le personnage appartient à l'utilisateur connecté avant de le modifier !!!
        User currentUser = getCurrentUser();
        if (!existing.getUser().equals(currentUser)) { // Comparaison des objets User
            throw new WitcherToolkitExeption("error.personnage.forbidden");
        }

        // 2. Copier les propriétés non-null du DTO vers l'entité existante
        BeanUtils.copyProperties(personnageVolatile, existing, ObjectUtils.getNullPropertyNames(personnageVolatile));

        // IMPORTANT: Si le DTO contient des listes d'associations (compétences, rituels...),
        // il faut une logique plus poussée pour les mettre à jour (ajouter/supprimer/modifier).
        // Actuellement, BeanUtils.copyProperties ne gère pas cela finement.

        // 3. Sauvegarder
        Personnage saved = personnageRepository.save(existing);

        log.info("Personnage mis à jour - ID : {}", saved.getIdPersonnage());

        // 4. Retourner le DTO
        return personnageMapper.toPersonnageDto(saved);
    }

    /**
     * Supprime un personnage par son ID.
     */
    @Override
    public void deletePersonnage(UUID idPersonnage) {
        if (idPersonnage == null) {
            throw new WitcherToolkitExeption("error.personnage.id.null");
        }

        Personnage personnage = personnageRepository.findById(idPersonnage)
                .orElseThrow(() -> new WitcherToolkitExeption("error.personnage.notfound", new Object[]{idPersonnage}));

        // Vérifier si le personnage appartient à l'utilisateur connecté avant de le supprimer
        User currentUser = getCurrentUser();
        if (!personnage.getUser().equals(currentUser)) { // Comparaison des objets User
            throw new WitcherToolkitExeption("error.personnage.forbidden");
        }

        personnageRepository.delete(personnage);
        log.info("Personnage supprimé - ID : {}", idPersonnage);
    }
}