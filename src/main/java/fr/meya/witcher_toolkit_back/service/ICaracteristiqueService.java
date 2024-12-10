package fr.meya.witcher_toolkit_back.service;

import fr.meya.witcher_toolkit_back.model.Caracteristique;

import java.util.List;

public interface ICaracteristiqueService {

	Caracteristique createCaracteristique(Caracteristique caracteristique);

	List<Caracteristique> getCaracteristiqueList();

	Caracteristique getCaracteristiqueById(int id);

}
