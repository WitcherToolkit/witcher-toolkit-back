package fr.meya.witcher.application.service;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
	void test_getCaracteristique_nominal() {
		Caracteristique caracteristique = new Caracteristique();
		caracteristique.setIdCaracteristique(1L);
		caracteristique.setNom("Force");

		Mockito.when(caracteristiqueRepository.findById(1L)).thenReturn(Optional.of(caracteristique));

		Caracteristique result = caracteristiqueService.getCaracteristique(1L);

		assertNotNull(result);
		assertEquals("Force", result.getNom());
	}

	@Test
	void test_getCaracteristique_idNull() {
		Exception exception = assertThrows(WitcherToolkitExeption.class, () -> caracteristiqueService.getCaracteristique(null));
		assertEquals("L'ID de la caractéristique est null.", exception.getMessage());
	}

	@Test
	void test_getCaracteristique_nonExistant() {
		Mockito.when(caracteristiqueRepository.findById(999L)).thenReturn(Optional.empty());

		Exception exception = assertThrows(WitcherToolkitExeption.class, () -> caracteristiqueService.getCaracteristique(999L));
		assertEquals("La caractéristique avec l'ID 999 n'existe pas.", exception.getMessage());
	}

	@Test
	void test_getCaracteristiqueList_empty() {
		Mockito.when(caracteristiqueRepository.findAll()).thenReturn(Collections.emptyList());

		List<Caracteristique> result = caracteristiqueService.getCaracteristiqueList();

		assertNotNull(result);
		assertTrue(result.isEmpty());
		Mockito.verify(caracteristiqueRepository, Mockito.times(1)).findAll();
	}

	@Test
	void test_createCaracteristique_invalidCode() {
		CaracteristiqueVolatile invalidCaracteristique = new CaracteristiqueVolatile("Force", "TOO_LONG_CODE", "Description");

		Exception exception = assertThrows(WitcherToolkitExeption.class,
				() -> caracteristiqueService.createCaracteristique(invalidCaracteristique));

		assertEquals("Le code de la caractéristique ne peut pas dépasser 6 caractères.", exception.getMessage());
	}

	@Test
	void test_createCaracteristique_invalidDescription() {
		CaracteristiqueVolatile invalidCaracteristique = new CaracteristiqueVolatile("Force", "FOR", "");

		Exception exception = assertThrows(WitcherToolkitExeption.class,
				() -> caracteristiqueService.createCaracteristique(invalidCaracteristique));

		assertEquals("La description de la caractéristique est obligatoire.", exception.getMessage());
	}

	@Test
	void test_updateCaracteristique_partialUpdate() {
		Caracteristique existingCaracteristique = new Caracteristique(1L, "Force", "FOR", "Description");
		Caracteristique updatedCaracteristique = new Caracteristique(1L, "Force améliorée", "FOR", "Nouvelle description");

		CaracteristiqueVolatile updateData = new CaracteristiqueVolatile("Force améliorée", null, "Nouvelle description");

		Mockito.when(caracteristiqueRepository.findById(1L)).thenReturn(Optional.of(existingCaracteristique));
		Mockito.when(caracteristiqueRepository.save(Mockito.any(Caracteristique.class))).thenReturn(updatedCaracteristique);

		Caracteristique result = caracteristiqueService.updateCaracteristique(1L, updateData);

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

		Caracteristique result = caracteristiqueService.updateCaracteristique(1L, updateData);

		assertNotNull(result);
		assertEquals("Force", result.getNom());
		assertEquals("FOR", result.getCode());
		assertEquals("Description", result.getDescription()); // Rien n’est modifié.
	}

	@Test
	void test_isValid_nullObject() {
		Exception exception = assertThrows(WitcherToolkitExeption.class,
				() -> caracteristiqueService.isValid(null));

		assertEquals("Aucune caractéristique fournie.", exception.getMessage());
	}

	@Test
	void test_isValid_nomVide() {
		CaracteristiqueVolatile invalidCaracteristique = new CaracteristiqueVolatile("", "FOR", "Description");

		Exception exception = assertThrows(WitcherToolkitExeption.class,
				() -> caracteristiqueService.isValid(invalidCaracteristique));

		assertEquals("Le nom de la caractéristique est obligatoire.", exception.getMessage());
	}

	@Test
	void test_isValid_nomTooLong() {
		CaracteristiqueVolatile invalidCaracteristique = new CaracteristiqueVolatile("NomTrèsTrèsTrèsTrèsLong", "FOR", "Description");

		Exception exception = assertThrows(WitcherToolkitExeption.class,
				() -> caracteristiqueService.isValid(invalidCaracteristique));

		assertEquals("Le nom de la caractéristique ne peut pas dépasser 16 caractères.", exception.getMessage());
	}

	@Test
	void test_isValid_codeVide() {
		CaracteristiqueVolatile invalidCaracteristique = new CaracteristiqueVolatile("Force", "", "Description");

		Exception exception = assertThrows(WitcherToolkitExeption.class,
				() -> caracteristiqueService.isValid(invalidCaracteristique));

		assertEquals("Le code de la caractéristique est obligatoire.", exception.getMessage());
	}

	@Test
	void test_isValid_codeTooLong() {
		CaracteristiqueVolatile invalidCaracteristique = new CaracteristiqueVolatile("Force", "TOO_LONG", "Description");

		Exception exception = assertThrows(WitcherToolkitExeption.class,
				() -> caracteristiqueService.isValid(invalidCaracteristique));

		assertEquals("Le code de la caractéristique ne peut pas dépasser 6 caractères.", exception.getMessage());
	}

	@Test
	void test_isValid_nominalCase() {
		// Arrange : Création d'un objet valide
		CaracteristiqueVolatile validCaracteristique = new CaracteristiqueVolatile("Force", "FOR", "Description");

		// Action : Appeler la méthode à tester
		boolean result = caracteristiqueService.isValid(validCaracteristique);

		// Assert : Vérifiez que la méthode retourne true
		assertTrue(result);
	}

	@Test
	void test_deleteCaracteristique_nominal() {
		// Arrange : Création d'une caractéristique existante
		Caracteristique caracteristique = new Caracteristique(1L, "Force", "FOR", "Description");

		Mockito.when(caracteristiqueRepository.findById(1L)).thenReturn(Optional.of(caracteristique));

		// Action : Appel de la méthode à tester
		Caracteristique result = caracteristiqueService.deleteCaracteristique(1L);

		// Assert : Vérifiez le comportement et le résultat
		Mockito.verify(caracteristiqueRepository).delete(caracteristique);
		assertNotNull(result);
		assertEquals("Force", result.getNom());
	}

	@Test
	void test_deleteCaracteristique_idNull() {
		// Action et Assert : Vérifiez si une exception est levée correctement
		Exception exception = assertThrows(WitcherToolkitExeption.class,
				() -> caracteristiqueService.deleteCaracteristique(null));

		assertEquals("L'ID de la caractéristique est null.", exception.getMessage());
	}

	@Test
	void test_deleteCaracteristique_notFound() {
		// Arrange : Simuler un ID qui n'existe pas dans la base
		Mockito.when(caracteristiqueRepository.findById(999L)).thenReturn(Optional.empty());

		// Action et Assert : Vérifiez si une exception est levée correctement
		Exception exception = assertThrows(WitcherToolkitExeption.class,
				() -> caracteristiqueService.deleteCaracteristique(999L));

		assertEquals("La caractéristique avec l'ID 999 n'existe pas.", exception.getMessage());
	}

	@Test
	void test_createCaracteristique_nominal() {
		// Arrange : Création d'un objet valide
		CaracteristiqueVolatile caracteristiqueVolatile = new CaracteristiqueVolatile("Force", "FOR", "Description");
		Caracteristique savedCaracteristique = new Caracteristique(1L, "Force", "FOR", "Description");

		Mockito.when(caracteristiqueRepository.save(Mockito.any(Caracteristique.class)))
				.thenReturn(savedCaracteristique);

		// Action : Appeler la méthode à tester
		Caracteristique result = caracteristiqueService.createCaracteristique(caracteristiqueVolatile);

		// Assert : Vérifiez que la méthode retourne un objet valide
		assertNotNull(result);
		assertEquals(savedCaracteristique.getNom(), result.getNom());
		assertEquals(savedCaracteristique.getCode(), result.getCode());
		assertEquals(savedCaracteristique.getDescription(), result.getDescription());
	}

	@Test
	void test_createCaracteristique_invalidCaracteristique() {
		// Arrange : Création d'un objet invalide (nom vide)
		CaracteristiqueVolatile invalidCaracteristique = new CaracteristiqueVolatile("", "FOR", "Description");

		// Action et Assert : Vérifiez qu'une exception est levée
		Exception exception = assertThrows(WitcherToolkitExeption.class,
				() -> caracteristiqueService.createCaracteristique(invalidCaracteristique));

		assertEquals("Le nom de la caractéristique est obligatoire.", exception.getMessage()); // Message exact attendu
	}

	@Test
	void test_createCaracteristique_repositoryError() {
		// Arrange : Création d'un objet valide
		CaracteristiqueVolatile caracteristiqueVolatile = new CaracteristiqueVolatile("Force", "FOR", "Description");

		// Simuler une erreur dans le repository
		Mockito.when(caracteristiqueRepository.save(Mockito.any(Caracteristique.class)))
				.thenThrow(new RuntimeException("Erreur de base de données"));

		// Action et Assert : Vérifiez qu'une exception est levée
		Exception exception = assertThrows(RuntimeException.class,
				() -> caracteristiqueService.createCaracteristique(caracteristiqueVolatile));

		assertEquals("Erreur de base de données", exception.getMessage());
	}

	@Test
	void test_createCaracteristique_verifyMapping() {
		// Arrange : Création d'un objet de test valide
		CaracteristiqueVolatile caracteristiqueVolatile = new CaracteristiqueVolatile("Force", "FOR", "Description");

		Caracteristique mappedCaracteristique = new Caracteristique();
		mappedCaracteristique.setNom("Force");
		mappedCaracteristique.setCode("FOR");
		mappedCaracteristique.setDescription("Description");

		Mockito.when(caracteristiqueRepository.save(Mockito.any(Caracteristique.class)))
				.thenReturn(mappedCaracteristique);

		// Action : Appeler la méthode à tester
		Caracteristique result = caracteristiqueService.createCaracteristique(caracteristiqueVolatile);

		// Assert : Vérifiez que le mapping est correct
		assertEquals(caracteristiqueVolatile.getNom(), result.getNom());
		assertEquals(caracteristiqueVolatile.getCode(), result.getCode());
		assertEquals(caracteristiqueVolatile.getDescription(), result.getDescription());
	}

	@Test
	void test_createCaracteristique_invalidDataThrowsException() {
		// Arrange : Créer un objet invalide
		CaracteristiqueVolatile invalidCaracteristique = new CaracteristiqueVolatile("", "", "Description");

		// Simuler un retour "false" pour `isValid`
		CaracteristiqueService serviceMock = Mockito.spy(caracteristiqueService);
		Mockito.doReturn(false).when(serviceMock).isValid(invalidCaracteristique);

		// Action et Assert : Vérifiez que l'exception est bien levée
		Exception exception = assertThrows(WitcherToolkitExeption.class,
				() -> serviceMock.createCaracteristique(invalidCaracteristique));

		assertEquals("Les informations de la caractéristique ne sont pas valides.", exception.getMessage());
	}

	@Test
	void test_updateCaracteristique_nullIdThrowsException() {
		// Arrange : Préparez des données avec un ID null
		Long nullId = null;
		CaracteristiqueVolatile volatileData = new CaracteristiqueVolatile("Force", "FOR", "Nouvelle description");

		// Action et Assert : Vérifiez que l'exception est levée
		Exception exception = assertThrows(WitcherToolkitExeption.class,
				() -> caracteristiqueService.updateCaracteristique(nullId, volatileData));

		assertEquals("L'ID de la caractéristique est null.", exception.getMessage());
	}

	@Test
	void test_updateCaracteristique_nullUpdateDataThrowsException() {
		// Arrange : Préparez des données "null" pour l'objet à mettre à jour
		Long id = 1L;

		// Action et Assert : Vérifiez que l'exception est levée
		Exception exception = assertThrows(WitcherToolkitExeption.class,
				() -> caracteristiqueService.updateCaracteristique(id, null));

		assertEquals("Les données de mise à jour sont nulles.", exception.getMessage());
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
		Caracteristique updatedCaracteristique = caracteristiqueService.updateCaracteristique(1L, updateData);

		// Assert : Vérifiez que seul le code est modifié
		assertNotNull(updatedCaracteristique);
		assertEquals("NEW_CODE", updatedCaracteristique.getCode());
		assertEquals("Force", updatedCaracteristique.getNom()); // Le nom reste inchangé
		assertEquals("Description", updatedCaracteristique.getDescription()); // La description reste inchangée
	}

}
