package fr.meya.witcher.application.service;

import fr.meya.witcher.application.mapper.CaracteristiqueMapper;
import fr.meya.witcher.common.utils.ValidationUtils;
import fr.meya.witcher.domain.model.persistent.Caracteristique;
import fr.meya.witcher.exeption.WitcherToolkitExeption;
import fr.meya.witcher.infrastructure.adapter.out.ICaracteristiqueRepository;
import fr.meya.witcher.message.response.CaracteristiqueVolatile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CaracteristiqueServiceTest {

	@Mock
	private ICaracteristiqueRepository caracteristiqueRepository;

	@Mock
	private CaracteristiqueMapper caracteristiqueMapper;

	@Mock
	private ValidationUtils validationUtils;

	@InjectMocks
	private CaracteristiqueService testedClasse;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	//#region isValid
	@Test
	void test_isValid_nominalCase() {
		// Arrange : Création d'un objet valide
		CaracteristiqueVolatile validCaracteristique = new CaracteristiqueVolatile("Force", "FOR", "Description");

		// Action : Appeler la méthode à tester
		boolean result = testedClasse.isValid(validCaracteristique);

		// Assert : Vérifiez que la méthode retourne true
		assertTrue(result);
	}
	//#endregion isValid

	//#region createCaracteristique
	@Test
	void test_createCaracteristique_nominal() {
		// Arrange : Création d'un objet valide
		Caracteristique mappedCaracteristique = new Caracteristique(1L, "Force", "FOR", "Description");
		CaracteristiqueVolatile caracteristiqueVolatile = new CaracteristiqueVolatile("Force", "FOR", "Description");
		Caracteristique savedCaracteristique = new Caracteristique(1L, "Force", "FOR", "Description");

		Mockito.when(caracteristiqueMapper.toCaracteristiqueEntity(caracteristiqueVolatile)).thenReturn(mappedCaracteristique);
		Mockito.when(caracteristiqueRepository.save(Mockito.any(Caracteristique.class))).thenReturn(savedCaracteristique);

		// Action : Appeler la méthode à tester
		Caracteristique result = testedClasse.createCaracteristique(caracteristiqueVolatile);

		// Assert : Vérifiez que la méthode retourne un objet valide
		assertNotNull(result);
		assertEquals(savedCaracteristique.getNom(), result.getNom());
		assertEquals(savedCaracteristique.getCode(), result.getCode());
		assertEquals(savedCaracteristique.getDescription(), result.getDescription());

		Mockito.verify(caracteristiqueMapper, Mockito.times(1)).toCaracteristiqueEntity(caracteristiqueVolatile);
		Mockito.verify(caracteristiqueRepository, Mockito.times(1)).save(mappedCaracteristique);
	}

	@Test
	void test_createCaracteristique_invalidCaracteristique() {
		// Arrange : Création d'un objet invalide (nom vide)
		CaracteristiqueVolatile invalidCaracteristique = new CaracteristiqueVolatile("", "FOR", "Description");

		// Mock du message de validation
		Mockito.doThrow(new WitcherToolkitExeption("error.validation.generic"))
				.when(validationUtils).validateWithRules(Mockito.eq(invalidCaracteristique), Mockito.anyMap());

		// Action et Assert : Vérifiez qu'une exception est levée
		Exception exception = assertThrows(WitcherToolkitExeption.class,
				() -> testedClasse.createCaracteristique(invalidCaracteristique));

		assertEquals("error.validation.generic", exception.getMessage());
	}

	@Test
	void test_isValid_nullCaracteristique() {
		// Arrange : aucun objet (null)

		// Action et Assert : Vérifiez qu'une exception est levée
		Exception exception = assertThrows(WitcherToolkitExeption.class,
				() -> testedClasse.isValid(null));

		// Vérifiez le message de l'exception
		assertEquals("error.caracteristique.null", exception.getMessage());
	}

	@Test
	void test_createCaracteristique_verifyMapping() {
		// Arrange : Création d'un objet de test valide
		CaracteristiqueVolatile caracteristiqueVolatile = new CaracteristiqueVolatile("Force", "FOR", "Description");
		Caracteristique mappedCaracteristique = new Caracteristique(1L, "Force", "FOR", "Description");

		Mockito.when(caracteristiqueMapper.toCaracteristiqueEntity(caracteristiqueVolatile)).thenReturn(mappedCaracteristique);
		Mockito.when(caracteristiqueRepository.save(Mockito.any(Caracteristique.class))).thenReturn(mappedCaracteristique);

		// Action : Appeler la méthode à tester
		Caracteristique result = testedClasse.createCaracteristique(caracteristiqueVolatile);

		// Assert : Vérifiez que le mapping est correct
		assertEquals(caracteristiqueVolatile.getNom(), result.getNom());
		assertEquals(caracteristiqueVolatile.getCode(), result.getCode());
		assertEquals(caracteristiqueVolatile.getDescription(), result.getDescription());

		Mockito.verify(caracteristiqueMapper, Mockito.times(1)).toCaracteristiqueEntity(caracteristiqueVolatile);
	}

	@Test
	void test_createCaracteristique_invalidDataThrowsException() {
		// Arrange : Créer un objet invalide
		CaracteristiqueVolatile invalidCaracteristique = new CaracteristiqueVolatile("", "COR", "Description");

		// Simuler un retour "false" pour `isValid`
		Mockito.doThrow(new WitcherToolkitExeption("error.validation.generic"))
				.when(validationUtils).validateWithRules(Mockito.eq(invalidCaracteristique), Mockito.anyMap());

		// Action et Assert : Vérifiez que l'exception est bien levée
		Exception exception = assertThrows(WitcherToolkitExeption.class,
				() -> testedClasse.createCaracteristique(invalidCaracteristique));

		assertEquals("error.validation.generic", exception.getMessage());
	}
	//#endregion createCaracteristique

	//#region getCaracteristique
	@Test
	void test_getCaracteristique_nominal() {
		Caracteristique caracteristique = new Caracteristique();
		caracteristique.setIdCaracteristique(1L);
		caracteristique.setNom("Force");

		Mockito.when(caracteristiqueRepository.findById(1L)).thenReturn(Optional.of(caracteristique));

		Caracteristique result = testedClasse.getCaracteristique(1L);

		assertNotNull(result);
		assertEquals("Force", result.getNom());
	}

	@Test
	void test_getCaracteristique_idNull() {
		Exception exception = assertThrows(WitcherToolkitExeption.class, () -> testedClasse.getCaracteristique(null));
		assertEquals("L'ID de la caractéristique est null.", exception.getMessage());
	}

	@Test
	void test_getCaracteristique_nonExistant() {
		Mockito.when(caracteristiqueRepository.findById(999L)).thenReturn(Optional.empty());

		Exception exception = assertThrows(WitcherToolkitExeption.class, () -> testedClasse.getCaracteristique(999L));
		assertEquals("La caractéristique avec l'ID 999 n'existe pas.", exception.getMessage());
	}
	//#endregion getCaracteristique

	//#region getCaracteristiqueList
	@Test
	void test_getCaracteristiqueList_empty() {
		// Arrange : Simuler une base vide
		Mockito.when(caracteristiqueRepository.findAll()).thenReturn(Collections.emptyList());

		// Action : Appeler la méthode
		List<CaracteristiqueVolatile> result = testedClasse.getCaracteristiqueList();

		// Assert : Vérifiez qu'aucune erreur n'est levée et que le mapper n'est pas appelé
		assertNotNull(result);
		assertTrue(result.isEmpty());
		Mockito.verify(caracteristiqueRepository, Mockito.times(1)).findAll();
		Mockito.verifyNoInteractions(caracteristiqueMapper);
	}
	//#endregion getCaracteristiqueList

	//#region updateCaracteristique
	@Test
	void test_updateCaracteristique_partialUpdate() {
		Caracteristique existingCaracteristique = new Caracteristique(1L, "Force", "FOR", "Description");
		Caracteristique updatedCaracteristique = new Caracteristique(1L, "Force améliorée", "FOR", "Nouvelle description");

		CaracteristiqueVolatile updateData = new CaracteristiqueVolatile("Force améliorée", null, "Nouvelle description");

		Mockito.when(caracteristiqueRepository.findById(1L)).thenReturn(Optional.of(existingCaracteristique));
		Mockito.when(caracteristiqueRepository.save(Mockito.any(Caracteristique.class))).thenReturn(updatedCaracteristique);

		Caracteristique result = testedClasse.updateCaracteristique(1L, updateData);

		assertNotNull(result);
		assertEquals("Force améliorée", result.getNom());
		assertEquals("FOR", result.getCode()); // Le code n'est pas modifié.
		assertEquals("Nouvelle description", result.getDescription());
	}

	@Test
	void test_updateCaracteristique_noFieldsUpdated() {
		Caracteristique existingCaracteristique = new Caracteristique(1L, "Force", "FOR", "Description");

		CaracteristiqueVolatile updateData = new CaracteristiqueVolatile(null, null, null);

		Mockito.when(caracteristiqueRepository.findById(1L)).thenReturn(Optional.of(existingCaracteristique));
		Mockito.when(caracteristiqueRepository.save(Mockito.any(Caracteristique.class))).then(invocation -> invocation.getArgument(0));

		Caracteristique result = testedClasse.updateCaracteristique(1L, updateData);

		assertNotNull(result);
		assertEquals("Force", result.getNom());
		assertEquals("FOR", result.getCode());
		assertEquals("Description", result.getDescription()); // Rien n’est modifié.
	}

	@Test
	void test_updateCaracteristique_updateCode() {
		// Arrange : Créer une caractéristique existante
		Caracteristique existingCaracteristique = new Caracteristique(1L, "Force", "FOR", "Description");

		CaracteristiqueVolatile updateData = new CaracteristiqueVolatile(null, "NEW_CODE", null); // Seul le code est modifié

		Mockito.when(caracteristiqueRepository.findById(1L)).thenReturn(Optional.of(existingCaracteristique));
		Mockito.when(caracteristiqueRepository.save(Mockito.any(Caracteristique.class)))
				.thenAnswer(invocation -> invocation.getArgument(0)); // Retourne l'objet sauvegardé

		// Action : Appeler la méthode à tester
		Caracteristique updatedCaracteristique = testedClasse.updateCaracteristique(1L, updateData);

		// Assert : Vérifiez que seul le code est modifié
		assertNotNull(updatedCaracteristique);
		assertEquals("NEW_CODE", updatedCaracteristique.getCode());
		assertEquals("Force", updatedCaracteristique.getNom()); // Le nom reste inchangé
		assertEquals("Description", updatedCaracteristique.getDescription()); // La description reste inchangée
	}
	//#endregion updateCaracteristique

	//#region deleteCaracteristique
	@Test
	void test_deleteCaracteristique_nominal() {
		// Arrange : Créer une entité existante
		Caracteristique caracteristique = new Caracteristique(1L, "Force", "FOR", "Description");
		Mockito.when(caracteristiqueRepository.findById(1L)).thenReturn(Optional.of(caracteristique));

		// Action : Appeler la méthode
		testedClasse.deleteCaracteristique(1L);

		// Assert : Vérifiez que l'entité a été récupérée et supprimée
		Mockito.verify(caracteristiqueRepository, Mockito.times(1)).findById(1L);
		Mockito.verify(caracteristiqueRepository, Mockito.times(1)).delete(caracteristique);
	}

	@Test
	void test_deleteCaracteristique_idNull() {
		// Action et Assert : Vérifiez si une exception est levée correctement
		Exception exception = assertThrows(WitcherToolkitExeption.class,
				() -> testedClasse.deleteCaracteristique(null));

		assertEquals("L'ID de la caractéristique est null.", exception.getMessage());
	}

	@Test
	void test_deleteCaracteristique_notFound() {
		// Arrange : Simuler un ID qui n'existe pas dans la base
		Mockito.when(caracteristiqueRepository.findById(999L)).thenReturn(Optional.empty());

		// Action et Assert : Vérifiez si une exception est levée correctement
		Exception exception = assertThrows(WitcherToolkitExeption.class,
				() -> testedClasse.deleteCaracteristique(999L));

		assertEquals("La caractéristique avec l'ID 999 n'existe pas.", exception.getMessage());
	}
	//#endregion deleteCaracteristique
	
}
