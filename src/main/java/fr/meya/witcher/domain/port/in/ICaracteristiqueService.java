package fr.meya.witcher.domain.port.in;

import fr.meya.witcher.message.response.CaracteristiqueVolatile;
import fr.meya.witcher.domain.model.persistent.Caracteristique;

import java.util.List;
import java.util.UUID;

public interface ICaracteristiqueService {

	boolean isValid(CaracteristiqueVolatile caracteristiqueVolatile);

	List<CaracteristiqueVolatile> getCaracteristiqueList();

	Caracteristique getCaracteristique(UUID idCaracteristique);

	Caracteristique createCaracteristique(CaracteristiqueVolatile caracteristique);

	Caracteristique updateCaracteristique(UUID idCaracteristique, CaracteristiqueVolatile caracteristiqueVolatile);

	void deleteCaracteristique(UUID idCaracteristique);

}
