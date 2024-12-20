package fr.meya.witcher.application.service;

import fr.meya.witcher.domain.model.persistent.Envoutement;
import fr.meya.witcher.domain.port.in.IEnvoutementService;
import fr.meya.witcher.exeption.WitcherToolkitExeption;
import fr.meya.witcher.infrastructure.adapter.out.IEnvoutementRepository;
import fr.meya.witcher.message.response.EnvoutementVolatile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnvoutementService implements IEnvoutementService {

    private final IEnvoutementRepository envoutementRepository;

    public EnvoutementService(IEnvoutementRepository envoutementRepository) {
        this.envoutementRepository = envoutementRepository;
    }

    @Override
    public boolean isValid(EnvoutementVolatile envoutementVolatile) {
        if (envoutementVolatile == null) {
            throw new WitcherToolkitExeption("Aucun envoutement fournie.");
        }

        if (envoutementVolatile.getNom() == null || envoutementVolatile.getNom().isBlank()) {
            throw new WitcherToolkitExeption("Le nom de l'envoutement est obligatoire.");
        }

        if (envoutementVolatile.getNom().length() > 16) {
            throw new WitcherToolkitExeption("Le nom de l'envoutement doit faire maximum 16 caractères.");
        }

        if (envoutementVolatile.getCout() == null || envoutementVolatile.getCout().isBlank()) {
            throw new WitcherToolkitExeption("Le coût de l'envoutement est obligatoire.");
        }

        if (envoutementVolatile.getCout().length() > 10) {
            throw new WitcherToolkitExeption("Le coût de l'envoutement doit faire maximum 16 caractères.");
        }

        if (envoutementVolatile.getEffet() == null || envoutementVolatile.getEffet().isBlank()) {
            throw new WitcherToolkitExeption("L'effet de l'envoutement est obligatoire.");
        }

        if (envoutementVolatile.getPrerequis() == null || envoutementVolatile.getPrerequis().isBlank()) {
            throw new WitcherToolkitExeption("Le pre-requis de l'envoutement est obligatoire.");
        }

        if (envoutementVolatile.getDanger() == null || envoutementVolatile.getDanger().isBlank()) {
            throw new WitcherToolkitExeption("Le danger de l'envoutement est obligatoire.");
        }

        if (envoutementVolatile.getDanger().length() > 10) {
            throw new WitcherToolkitExeption("Le danger de l'envoutement doit faire maximum 6 caractères.");
        }

        return true;
    }

    @Override
    public Envoutement createEnvoutement(EnvoutementVolatile envoutementVolatile) {
        if (!isValid(envoutementVolatile)) {
            throw new WitcherToolkitExeption("Les informations de l'envoûtement ne sont pas valides.");
        }

        Envoutement envoutement = new Envoutement();
        envoutement.setNom(envoutementVolatile.getNom());
        envoutement.setCout(envoutementVolatile.getCout());
        envoutement.setEffet(envoutementVolatile.getEffet());
        envoutement.setPrerequis(envoutementVolatile.getPrerequis());
        envoutement.setDanger(envoutementVolatile.getDanger());

        return envoutementRepository.save(envoutement);
    }

    @Override
    public Envoutement getEnvoutement(Long idEnvoutement) {
        if (idEnvoutement == null) {
            throw new WitcherToolkitExeption("L'ID de l'envoûtement est null.");
        }

        return envoutementRepository.findById(idEnvoutement).orElseThrow(() -> new WitcherToolkitExeption("L'envoûtement avec l'ID " + idEnvoutement + " n'existe pas."));

    }

    @Override
    public Envoutement updateEnvoutement(Long idEnvoutement, EnvoutementVolatile envoutementVolatile) {
        if (idEnvoutement == null) {
            throw new WitcherToolkitExeption("L'ID de la competence est null.");
        }
        if (envoutementVolatile == null) {
            throw new WitcherToolkitExeption("Les données de mise à jour sont nulles.");
        }

        Envoutement envoutementExistant = getEnvoutement(idEnvoutement);

        if (envoutementVolatile.getNom() != null) {
            envoutementExistant.setNom(envoutementVolatile.getNom());
        }
        if (envoutementVolatile.getCout() != null) {
            envoutementExistant.setCout(envoutementVolatile.getCout());
        }
        if (envoutementVolatile.getEffet() != null) {
            envoutementExistant.setEffet(envoutementVolatile.getEffet());
        }
        if (envoutementVolatile.getPrerequis() != null) {
            envoutementExistant.setPrerequis(envoutementVolatile.getPrerequis());
        }
        if (envoutementVolatile.getDanger() != null) {
            envoutementExistant.setDanger(envoutementVolatile.getDanger());
        }

        return envoutementRepository.save(envoutementExistant);
    }

    @Override
    public Envoutement deleteEnvoutement(Long idEnvoutement) {
        Envoutement envoutementExistant = getEnvoutement(idEnvoutement);
        envoutementRepository.delete(envoutementExistant);
        return envoutementExistant;
    }

    @Override
    public List<EnvoutementVolatile> getEnvoutementList() {

        List<Envoutement> envoutementList = envoutementRepository.findAll();

        return envoutementList.stream().map(envoutement -> {
            EnvoutementVolatile envoutementVolatile = new EnvoutementVolatile();
            envoutementVolatile.setNom(envoutement.getNom());
            envoutementVolatile.setCout(envoutement.getCout());
            envoutementVolatile.setEffet(envoutement.getEffet());
            envoutementVolatile.setPrerequis(envoutement.getPrerequis());
            envoutementVolatile.setDanger(envoutement.getDanger());

            return envoutementVolatile;

        }).collect(Collectors.toList());
    }

}
