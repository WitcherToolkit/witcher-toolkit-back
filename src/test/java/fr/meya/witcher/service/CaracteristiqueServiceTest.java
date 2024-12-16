package fr.meya.witcher.service;

import fr.meya.witcher.adapter.port.out.ICaracteristiqueRepository;
import fr.meya.witcher.model.persistent.Caracteristique;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CaracteristiqueServiceTest {

	@Mock
	private ICaracteristiqueRepository caracteristiqueRepository;

	@InjectMocks
	private CaracteristiqueService caracteristiqueService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void test_findAll_renvois_une_liste_de_caracteristique() {

		List<Caracteristique> caracteristiqueList = new ArrayList<>();

		Caracteristique caracteristique1 = new Caracteristique();
		caracteristique1.setIdCaracteristique(1L);
		caracteristique1.setNom("Force");
		caracteristique1.setCode("FOR");
		caracteristique1.setDescription("Lorem ipsum dolor");
		caracteristiqueList.add(caracteristique1);

		Caracteristique caracteristique2 = new Caracteristique();
		caracteristique2.setIdCaracteristique(2L);
		caracteristique2.setNom("Agilité");
		caracteristique2.setCode("AGI");
		caracteristique2.setDescription("Lorem ipsum dolor");
		caracteristiqueList.add(caracteristique2);

		Mockito.when(caracteristiqueRepository.findAll()).thenReturn(caracteristiqueList);

		List<Caracteristique> result = caracteristiqueService.getCaracteristiqueList();

		Mockito.verify(caracteristiqueRepository).findAll();

		assertNotNull(result);
		assertEquals(2, result.size());
		assertEquals("Force", result.get(0).getNom());
		assertEquals("Agilité", result.get(1).getNom());

		Mockito.verify(caracteristiqueRepository, Mockito.times(1)).findAll();
	}

}
