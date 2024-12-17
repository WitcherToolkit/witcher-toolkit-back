package fr.meya.witcher.application.service;

import fr.meya.witcher.domain.model.persistent.Competence;
import fr.meya.witcher.exeption.WitcherToolkitExeption;
import fr.meya.witcher.infrastructure.adapter.out.ICompetenceRepository;
import fr.meya.witcher.message.response.CompetenceVolatile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CompetenceServiceTest {

	@Mock
	private ICompetenceRepository competenceRepository;

	@InjectMocks
	private CompetenceService competenceService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	//#region isValid
	@Test
	void test_isValid_nominalCase() {
		// Arrange : Création d'un objet valide
		CompetenceVolatile validCompetence = new CompetenceVolatile("Force",
				"FOR",
				"Description",
				"DescriptionBase10",
				"DescriptionBase13",
				"DescriptionBase16",
				"DescriptionBase20");

		// Action : Appeler la méthode à tester
		boolean result = competenceService.isValid(validCompetence);

		// Assert : Vérifier que le résultat est true
		assertTrue(result);
	}

	@Test
	void test_isValid_nullObject() {
		// Action et Assert : Vérifie qu'une exception est levée si l'objet est null
		Exception exception = assertThrows(WitcherToolkitExeption.class,
				() -> competenceService.isValid(null));

		assertEquals("Aucune competence fournie.", exception.getMessage());
	}

	@Test
	void test_isValid_nomVide() {
		CompetenceVolatile invalidCompetence = new CompetenceVolatile("",
				"FOR",
				"Description",
				"DescriptionBase10",
				"DescriptionBase13",
				"DescriptionBase16",
				"DescriptionBase20");

		Exception exception = assertThrows(WitcherToolkitExeption.class,
				() -> competenceService.isValid(invalidCompetence));

		assertEquals("Le nom de la competence est obligatoire.", exception.getMessage());
	}

	@Test
	void test_isValid_codeCaracteristiqueVide() {
		CompetenceVolatile invalidCompetence = new CompetenceVolatile("Force",
				"",
				"Description",
				"DescriptionBase10",
				"DescriptionBase13",
				"DescriptionBase16",
				"DescriptionBase20");

		Exception exception = assertThrows(WitcherToolkitExeption.class,
				() -> competenceService.isValid(invalidCompetence));

		assertEquals("Le code de la caractéristique de la competence est obligatoire.", exception.getMessage());
	}

	@Test
	void test_isValid_descriptionVide() {
		CompetenceVolatile invalidCompetence = new CompetenceVolatile("Force",
				"FOR",
				"",
				"DescriptionBase10",
				"DescriptionBase13",
				"DescriptionBase16",
				"DescriptionBase20");

		Exception exception = assertThrows(WitcherToolkitExeption.class,
				() -> competenceService.isValid(invalidCompetence));

		assertEquals("Le description de la competence est obligatoire.", exception.getMessage());
	}

	@Test
	void test_isValid_descriptionBaseVide() {
		CompetenceVolatile invalidCompetence = new CompetenceVolatile("Force",
				"FOR",
				"Description",
				"",
				"",
				"",
				"");

		Exception exception = assertThrows(WitcherToolkitExeption.class,
				() -> competenceService.isValid(invalidCompetence));

		assertEquals("Le description base 10 de la competence est obligatoire.", exception.getMessage());
	}
	//#endregion isValid

	//#region createCompetence
	@Test
	void test_createCompetence_nominal() {
		// Arrange : Création d'une compétence valide
		CompetenceVolatile competenceVolatile = new CompetenceVolatile("Force", "FOR", "Description", "Base10", "Base13", "Base16", "Base20");
		Competence savedCompetence = new Competence(1L, "Force", "FOR", "Description", "Base10", "Base13", "Base16", "Base20");

		Mockito.when(competenceRepository.save(Mockito.any(Competence.class))).thenReturn(savedCompetence);

		// Action : Appeler la méthode à tester
		Competence result = competenceService.createCompetence(competenceVolatile);

		// Assert : Vérifiez que la méthode retourne une compétence valide
		assertNotNull(result);
		assertEquals(savedCompetence.getNom(), result.getNom());
		assertEquals(savedCompetence.getCodeCaracteristique(), result.getCodeCaracteristique());
		assertEquals(savedCompetence.getDescription(), result.getDescription());
		assertEquals(savedCompetence.getDescriptionBase10(), result.getDescriptionBase10());
		assertEquals(savedCompetence.getDescriptionBase13(), result.getDescriptionBase13());
		assertEquals(savedCompetence.getDescriptionBase16(), result.getDescriptionBase16());
		assertEquals(savedCompetence.getDescriptionBase20(), result.getDescriptionBase20());
	}

	@Test
	void test_createCompetence_invalidCompetenceThrowsException() {
		// Arrange : Création d'une compétence valide
		CompetenceVolatile invalidCompetence = new CompetenceVolatile("", "", "", "", "", "", "");

		// Simuler un retour "false" pour `isValid`
		CompetenceService serviceMock = Mockito.spy(competenceService);
		Mockito.doReturn(false).when(serviceMock).isValid(invalidCompetence);

		// Action et Assert : Vérifiez que l'exception est bien levée
		Exception exception = assertThrows(WitcherToolkitExeption.class,
				() -> serviceMock.createCompetence(invalidCompetence));

		assertEquals("Les informations de la compétence ne sont pas valides.", exception.getMessage());

	}

	@Test
	void test_createCompetence_invalidCompetence() {
		// Arrange : Création d'une compétence invalide
		CompetenceVolatile invalidCompetence = new CompetenceVolatile("", "FOR", "Description", "Base10", "Base13", "Base16", "Base20");

		// Action et Assert : Vérifiez que les validations échouent
		Exception exception = assertThrows(WitcherToolkitExeption.class,
				() -> competenceService.createCompetence(invalidCompetence));

		assertEquals("Le nom de la competence est obligatoire.", exception.getMessage());
	}

	@Test
	void test_createCompetence_repositoryError() {
		// Arrange : Création d'une compétence valide
		CompetenceVolatile competenceVolatile = new CompetenceVolatile("Force", "FOR", "Description", "Base10", "Base13", "Base16", "Base20");

		// Simuler une erreur dans le repository
		Mockito.when(competenceRepository.save(Mockito.any(Competence.class))).thenThrow(new RuntimeException("Erreur de la base de données"));

		// Action et Assert : Vérifiez qu'une exception est levée
		Exception exception = assertThrows(RuntimeException.class,
				() -> competenceService.createCompetence(competenceVolatile));

		assertEquals("Erreur de la base de données", exception.getMessage());
	}
	//#endregion createCompetence

	//#region getCompetence
	@Test
	void test_getCompetence_nominal() {
		// Arrange : Création d'une compétence fictive
		Competence competence = new Competence(1L, "Force", "FOR", "Description", "Base10", "Base13", "Base16", "Base20");
		Mockito.when(competenceRepository.findById(1L)).thenReturn(Optional.of(competence));

		// Action : Appeler la méthode
		Competence result = competenceService.getCompetence(1L);

		// Assert : Vérifications
		assertNotNull(result);
		assertEquals("Force", result.getNom());
	}

	@Test
	void test_getCompetence_idNull() {
		Exception exception = assertThrows(WitcherToolkitExeption.class,
				() -> competenceService.getCompetence(null));

		assertEquals("L'ID de la competence est null.", exception.getMessage());
	}

	@Test
	void test_getCompetence_nonExistant() {
		Mockito.when(competenceRepository.findById(999L)).thenReturn(Optional.empty());

		Exception exception = assertThrows(WitcherToolkitExeption.class,
				() -> competenceService.getCompetence(999L));

		assertEquals("La competence avec l'ID 999 n'existe pas.", exception.getMessage());
	}
	//#endregion getCompetence

	//#region updateCompetence
	@Test
	void test_updateCompetence_partialUpdate() {
		// Arrange : Préparation d'une compétence existante et des données de mise à jour
		Competence existingCompetence = new Competence(1L, "Force", "FOR", "Description", "Base10", "Base13", "Base16", "Base20");
		Competence updatedCompetence = new Competence(1L, "Force Améliorée", "FOR", "Nouvelle Description", "NewBase10", "NewBase13", "Base16", "Base20");

		CompetenceVolatile updateData = new CompetenceVolatile("Force Améliorée", null, "Nouvelle Description", "NewBase10", "NewBase13", null, null);

		Mockito.when(competenceRepository.findById(1L)).thenReturn(Optional.of(existingCompetence));
		Mockito.when(competenceRepository.save(Mockito.any(Competence.class))).thenReturn(updatedCompetence);

		// Action : Appeler la méthode à tester
		Competence result = competenceService.updateCompetence(1L, updateData);

		// Assert : Vérifier les champs mis à jour
		assertNotNull(result);
		assertEquals("Force Améliorée", result.getNom());
		assertEquals("FOR", result.getCodeCaracteristique()); // Le code reste le même
		assertEquals("Nouvelle Description", result.getDescription());
		assertEquals("NewBase10", result.getDescriptionBase10());
		assertEquals("NewBase13", result.getDescriptionBase13());
		assertEquals("Base16", result.getDescriptionBase16()); // Pas modifié
		assertEquals("Base20", result.getDescriptionBase20()); // Pas modifié
	}

	@Test
	void test_updateCompetence_noFieldsUpdated() {
		// Arrange : Une compétence existante et un objet de mise à jour vide
		Competence existingCompetence = new Competence(1L, "Force", "FOR", "Description", "Base10", "Base13", "Base16", "Base20");
		CompetenceVolatile updateData = new CompetenceVolatile(null, null, null, null, null, null, null);

		Mockito.when(competenceRepository.findById(1L)).thenReturn(Optional.of(existingCompetence));
		Mockito.when(competenceRepository.save(Mockito.any(Competence.class))).then(invocation -> invocation.getArgument(0));

		// Action : Appeler la méthode à tester
		Competence result = competenceService.updateCompetence(1L, updateData);

		// Assert : Aucun champ ne doit être modifié
		assertNotNull(result);
		assertEquals(existingCompetence.getNom(), result.getNom());
		assertEquals(existingCompetence.getCodeCaracteristique(), result.getCodeCaracteristique());
		assertEquals(existingCompetence.getDescription(), result.getDescription());
		assertEquals(existingCompetence.getDescriptionBase10(), result.getDescriptionBase10());
		assertEquals(existingCompetence.getDescriptionBase13(), result.getDescriptionBase13());
		assertEquals(existingCompetence.getDescriptionBase16(), result.getDescriptionBase16());
		assertEquals(existingCompetence.getDescriptionBase20(), result.getDescriptionBase20());
	}

	@Test
	void test_updateCompetence_nullIdThrowsException() {
		// Arrange : ID null
		Long nullId = null;
		CompetenceVolatile updateData = new CompetenceVolatile("Force", "NEW", "Description", "Base10", "Base13", "Base16", "Base20");

		// Action et Assert : Vérifier qu'une exception est jetée
		Exception exception = assertThrows(WitcherToolkitExeption.class,
				() -> competenceService.updateCompetence(nullId, updateData));

		assertEquals("L'ID de la competence est null.", exception.getMessage());
	}

	@Test
	void test_updateCompetence_nullUpdateDataThrowsException() {
		// Arrange : Objet de mise à jour null
		Long id = 1L;

		// Action et Assert : Vérifier qu'une exception est jetée
		Exception exception = assertThrows(WitcherToolkitExeption.class,
				() -> competenceService.updateCompetence(id, null));

		assertEquals("Les données de mise à jour sont nulles.", exception.getMessage());
	}
	//#endregion updateCompetence

	//#region deleteCompetence
	@Test
	void test_deleteCompetence_nominal() {
		// Arrange : Préparation d'une compétence existante
		Competence existingCompetence = new Competence(1L, "Force", "FOR", "Description", "Base10", "Base13", "Base16", "Base20");

		Mockito.when(competenceRepository.findById(1L)).thenReturn(Optional.of(existingCompetence));

		// Action : Appeler la méthode à tester
		Competence result = competenceService.deleteCompetence(1L);

		// Assert : Vérifications
		Mockito.verify(competenceRepository).delete(existingCompetence);
		assertNotNull(result);
		assertEquals("Force", result.getNom());
	}

	@Test
	void test_deleteCompetence_idNull() {
		// Action et Assert : Vérifier qu'une exception est jetée
		Exception exception = assertThrows(WitcherToolkitExeption.class,
				() -> competenceService.deleteCompetence(null));

		assertEquals("L'ID de la competence est null.", exception.getMessage());
	}

	@Test
	void test_deleteCompetence_notFound() {
		// Arrange : Aucun élément trouvé dans le repository
		Mockito.when(competenceRepository.findById(999L)).thenReturn(Optional.empty());

		// Action et Assert : Vérifier qu'une exception est jetée
		Exception exception = assertThrows(WitcherToolkitExeption.class,
				() -> competenceService.deleteCompetence(999L));

		assertEquals("La competence avec l'ID 999 n'existe pas.", exception.getMessage());
	}
	//#endregion deleteCompetence

	//#region getCompetenceList
	@Test
	void test_getCompetenceList_empty() {
		// Arrange : Simuler une liste vide
		Mockito.when(competenceRepository.findAll()).thenReturn(Collections.emptyList());

		// Action : Appeler la méthode à tester
		List<Competence> result = competenceService.getCompetenceList();

		// Assert : La liste doit être vide
		assertNotNull(result);
		assertTrue(result.isEmpty());
		Mockito.verify(competenceRepository, Mockito.times(1)).findAll();
	}

	@Test
	void test_getCompetenceList_nominal() {
		// Arrange : Simuler une liste avec des compétences
		Competence competence1 = new Competence(1L, "Force", "FOR", "Description", "Base10", "Base13", "Base16", "Base20");
		Competence competence2 = new Competence(2L, "Endurance", "END", "Description", "Base10", "Base13", "Base16", "Base20");
		List<Competence> mockList = Arrays.asList(competence1, competence2);

		Mockito.when(competenceRepository.findAll()).thenReturn(mockList);

		// Action : Appeler la méthode à tester
		List<Competence> result = competenceService.getCompetenceList();

		// Assert : La liste doit contenir les éléments simulés
		assertNotNull(result);
		assertEquals(2, result.size());
		assertEquals("Force", result.get(0).getNom());
		assertEquals("Endurance", result.get(1).getNom());
		Mockito.verify(competenceRepository, Mockito.times(1)).findAll();
	}
	//#endregion getCompetenceList
}
