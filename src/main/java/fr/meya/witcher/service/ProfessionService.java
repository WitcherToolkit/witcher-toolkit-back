package fr.meya.witcher.service;

import fr.meya.witcher.model.persistent.Profession;
import fr.meya.witcher.port.out.IProfessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessionService implements IProfessionService{

    private final IProfessionRepository iProfessionRepository;

    public ProfessionService(IProfessionRepository iProfessionRepository) {
        this.iProfessionRepository = iProfessionRepository;
    }

    @Override
    public List<Profession> getProfessionList() {
        return iProfessionRepository.findAll();
    }

    @Override
    public List<Profession> exempleDeMethode(String name, String competence) {
        return iProfessionRepository.exempleDeMethode(name, competence);
    }
}
