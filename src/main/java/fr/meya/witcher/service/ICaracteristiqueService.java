package fr.meya.witcher.service;

import fr.meya.witcher.model.persistent.Caracteristique;

import java.util.List;

public interface ICaracteristiqueService {

	Caracteristique createCaracteristique(Caracteristique caracteristique);

	List<Caracteristique> getCaracteristiqueList();

	Caracteristique getCaracteristiqueById(int id);

}
