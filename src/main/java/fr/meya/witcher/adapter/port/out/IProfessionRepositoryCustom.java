package fr.meya.witcher.adapter.port.out;

import fr.meya.witcher.model.persistent.Profession;

import java.util.List;

public interface IProfessionRepositoryCustom {
    List<Profession> getListeDesProfessionsAvecCompetences();
}
