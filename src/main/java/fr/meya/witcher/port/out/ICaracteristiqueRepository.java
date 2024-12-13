package fr.meya.witcher.port.out;

import fr.meya.witcher.model.persistent.Caracteristique;

import java.util.List;

public interface ICaracteristiqueRepository {

	void create(Caracteristique caracteristique);

	List<Caracteristique> list();

	Caracteristique getById(int id);
}
