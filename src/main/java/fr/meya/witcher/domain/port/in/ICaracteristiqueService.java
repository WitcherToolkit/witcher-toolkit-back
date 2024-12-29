package fr.meya.witcher.domain.port.in;

import fr.meya.witcher.message.response.CaracteristiqueVolatile;
import fr.meya.witcher.domain.model.persistent.Caracteristique;

import java.util.List;

public interface ICaracteristiqueService {

	boolean isValid(CaracteristiqueVolatile caracteristiqueVolatile);

	List<CaracteristiqueVolatile> getCaracteristiqueList();

	Caracteristique getCaracteristique(Long idCaracteristique);

	Caracteristique createCaracteristique(CaracteristiqueVolatile caracteristique);

	Caracteristique updateCaracteristique(Long idCaracteristique, CaracteristiqueVolatile caracteristiqueVolatile);

	void deleteCaracteristique(Long idCaracteristique);

}
