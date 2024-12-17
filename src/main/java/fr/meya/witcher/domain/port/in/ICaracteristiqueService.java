package fr.meya.witcher.domain.port.in;

import fr.meya.witcher.message.response.CaracteristiqueVolatile;
import fr.meya.witcher.domain.model.persistent.Caracteristique;

import java.util.List;

public interface ICaracteristiqueService {

	Caracteristique createCaracteristique(Caracteristique caracteristique);

	Caracteristique getCaracteristique(Long idCaracteristique);

	Caracteristique updateCaracteristique(Long idCaracteristique, CaracteristiqueVolatile caracteristiqueVolatile);

	Caracteristique deleteCaracteristique(Long idCaracteristique);

	List<Caracteristique> getCaracteristiqueList();

}
