package fr.meya.witcher.port.out;

import fr.meya.witcher.model.persistent.Profession;

import java.util.List;

public interface IProfessionRepositoryCustom {
    List<Profession> exempleDeMethode(String name, String competence);
}
