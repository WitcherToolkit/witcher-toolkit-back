package fr.meya.witcher_toolkit_back.port.out;

import fr.meya.witcher_toolkit_back.model.persistent.Caracteristique;

import java.util.List;

public interface ICaracteristiqueRepository {

	void create(Caracteristique caracteristique);

	List<Caracteristique> list();

	Caracteristique getById(int id);
}
