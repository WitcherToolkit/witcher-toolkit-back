package fr.meya.witcher.service;

import fr.meya.witcher.model.message.CaracteristiqueVolatile;
import fr.meya.witcher.model.persistent.Caracteristique;

import java.util.List;

public interface ICaracteristiqueService {

	Caracteristique createCaracteristique(Caracteristique caracteristique);

	Caracteristique getCaracteristique(Long idCaracteristique);

	Caracteristique updateCaracteristique(Long idCaracteristique, CaracteristiqueVolatile caracteristiqueVolatile);

	Caracteristique deleteCaracteristique(Long idCaracteristique);

	List<Caracteristique> getCaracteristiqueList();

}
