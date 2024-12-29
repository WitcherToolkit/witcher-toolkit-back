package fr.meya.witcher.application.service;

import fr.meya.witcher.application.mapper.CompetenceMapper;
import fr.meya.witcher.common.utils.ValidationUtils;
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

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CompetenceServiceTest {

	@Mock
	private ICompetenceRepository competenceRepository;

	@Mock
	private CompetenceMapper competenceMapper;

	@Mock
	private ValidationUtils validationUtils;

	@InjectMocks
	private CompetenceService testedClasse;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	//#region isValid
	@Test
	void test_isValid_nominalCase() {
		// Arrange : Création d'un objet valide
		CompetenceVolatile validCompetence = new CompetenceVolatile("Force", "FOR", "Description", "DescriptionBase10", "DescriptionBase13", "DescriptionBase16", "DescriptionBase20");

		// Action : Appeler la méthode à tester
		boolean result = testedClasse.isValid(validCompetence);

		// Assert : Vérifier que le résultat est true
		assertTrue(result);
	}
	//#endregion isValid

	//#region createCompetence
	@Test
	void test_createCompetence_nominal() {
		// Arrange : Création d'un objet valide
		Competence mappedCompetence = new Competence(1L, "Force", "FOR", "Description", "DescriptionBase10", "DescriptionBase13", "DescriptionBase16", "DescriptionBase20");
		CompetenceVolatile competenceVolatile = new CompetenceVolatile("Force", "FOR", "Description", "DescriptionBase10", "DescriptionBase13", "DescriptionBase16", "DescriptionBase20");
		Competence savedCompetence = new Competence(1L, "Force", "FOR", "Description", "DescriptionBase10", "DescriptionBase13", "DescriptionBase16", "DescriptionBase20");

		Mockito.when(competenceMapper.toCompetenceEntity(competenceVolatile)).thenReturn(mappedCompetence);
		Mockito.when(competenceRepository.save(Mockito.any(Competence.class))).thenReturn(savedCompetence);

		// Action : Appeler la méthode à tester
		Competence result = testedClasse.createCompetence(competenceVolatile);

		// Assert : Vérifiez que la méthode retourne un objet valide
		assertNotNull(result);
		assertEquals(savedCompetence.getNom(), result.getNom());
		assertEquals(savedCompetence.getCodeCaracteristique(), result.getCodeCaracteristique());
		assertEquals(savedCompetence.getDescription(), result.getDescription());
		assertEquals(savedCompetence.getDescriptionBase10(), result.getDescriptionBase10());
		assertEquals(savedCompetence.getDescriptionBase13(), result.getDescriptionBase13());
		assertEquals(savedCompetence.getDescriptionBase16(), result.getDescriptionBase16());
		assertEquals(savedCompetence.getDescriptionBase20(), result.getDescriptionBase20());

		Mockito.verify(competenceMapper, Mockito.times(1)).toCompetenceEntity(competenceVolatile);
		Mockito.verify(competenceRepository, Mockito.times(1)).save(mappedCompetence);
	}

	@Test
	void test_createCompetence_invalidCompetence() {
		// Arrange : Création d'un objet invalide (nom vide)
		CompetenceVolatile invalidCompetence = new CompetenceVolatile("", "FOR", "Description", "DescriptionBase10", "DescriptionBase13", "DescriptionBase16", "DescriptionBase20");

		// Mock du message de validation
		Mockito.doThrow(new WitcherToolkitExeption("error.validation.generic"))
				.when(validationUtils).validateWithRules(Mockito.eq(invalidCompetence), Mockito.anyMap());

		// Action et Assert : Vérifiez qu'une exception est levée
		Exception exception = assertThrows(WitcherToolkitExeption.class,
				() -> testedClasse.createCompetence(invalidCompetence));

		assertEquals("error.validation.generic", exception.getMessage());
	}

	@Test
	void test_isValid_nullCompetence() {
		// Arrange : aucun objet (null)

		// Action et Assert : Vérifiez qu'une exception est levée
		Exception exception = assertThrows(WitcherToolkitExeption.class,
				() -> testedClasse.isValid(null));

		// Vérifiez le message de l'exception
		assertEquals("error.competence.null", exception.getMessage());
	}

	@Test
	void test_createCompetence_verifyMapping() {
		// Arrange : Création d'un objet de test valide
		CompetenceVolatile competenceVolatile = new CompetenceVolatile("Force", "FOR", "Description", "DescriptionBase10", "DescriptionBase13", "DescriptionBase16", "DescriptionBase20");
		Competence mappedCompetence = new Competence(1L, "Force", "FOR", "Description", "DescriptionBase10", "DescriptionBase13", "DescriptionBase16", "DescriptionBase20");

		Mockito.when(competenceMapper.toCompetenceEntity(competenceVolatile)).thenReturn(mappedCompetence);
		Mockito.when(competenceRepository.save(Mockito.any(Competence.class))).thenReturn(mappedCompetence);

		// Action : Appeler la méthode à tester
		Competence result = testedClasse.createCompetence(competenceVolatile);

		// Assert : Vérifiez que le mapping est correct
		assertEquals(competenceVolatile.getNom(), result.getNom());
		assertEquals(competenceVolatile.getNom(), result.getNom());
		assertEquals(competenceVolatile.getCodeCaracteristique(), result.getCodeCaracteristique());
		assertEquals(competenceVolatile.getDescription(), result.getDescription());
		assertEquals(competenceVolatile.getDescriptionBase10(), result.getDescriptionBase10());
		assertEquals(competenceVolatile.getDescriptionBase13(), result.getDescriptionBase13());
		assertEquals(competenceVolatile.getDescriptionBase16(), result.getDescriptionBase16());
		assertEquals(competenceVolatile.getDescriptionBase20(), result.getDescriptionBase20());

		Mockito.verify(competenceMapper, Mockito.times(1)).toCompetenceEntity(competenceVolatile);
	}

	@Test
	void test_createCompetence_invalidDataThrowsException() {
		// Arrange : Créer un objet invalide
		CompetenceVolatile invalidCompetence = new CompetenceVolatile("", "FOR", "Description", "DescriptionBase10", "DescriptionBase13", "DescriptionBase16", "DescriptionBase20");

		// Simuler un retour "false" pour `isValid`
		Mockito.doThrow(new WitcherToolkitExeption("error.validation.generic"))
				.when(validationUtils).validateWithRules(Mockito.eq(invalidCompetence), Mockito.anyMap());

		// Action et Assert : Vérifiez que l'exception est bien levée
		Exception exception = assertThrows(WitcherToolkitExeption.class,
				() -> testedClasse.createCompetence(invalidCompetence));

		assertEquals("error.validation.generic", exception.getMessage());
	}
	//#endregion createCompetence

	//#region getCompetence
	@Test
	void test_getCompetence_nominal() {
		// Arrange : Création d'une compétence fictive
		Competence competence = new Competence(1L, "Force", "FOR", "Description", "Base10", "Base13", "Base16", "Base20");
		Mockito.when(competenceRepository.findById(1L)).thenReturn(Optional.of(competence));

		// Action : Appeler la méthode
		Competence result = testedClasse.getCompetence(1L);

		// Assert : Vérifications
		assertNotNull(result);
		assertEquals("Force", result.getNom());
	}

	@Test
	void test_getCompetence_idNull() {
		Exception exception = assertThrows(WitcherToolkitExeption.class,
				() -> testedClasse.getCompetence(null));

		assertEquals("L'ID de la competence est null.", exception.getMessage());
	}

	@Test
	void test_getCompetence_nonExistant() {
		Mockito.when(competenceRepository.findById(999L)).thenReturn(Optional.empty());

		Exception exception = assertThrows(WitcherToolkitExeption.class,
				() -> testedClasse.getCompetence(999L));

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
		Competence result = testedClasse.updateCompetence(1L, updateData);

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
		Competence result = testedClasse.updateCompetence(1L, updateData);

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
	//#endregion updateCompetence

	//#region getCompetenceList
	@Test
	void test_getCompetenceList_empty() {
		// Arrange : Simuler une base vide
		Mockito.when(competenceRepository.findAll()).thenReturn(Collections.emptyList());

		// Action : Appeler la méthode
		List<CompetenceVolatile> result = testedClasse.getCompetenceList();

		// Assert : Vérifiez qu'aucune erreur n'est levée et que le mapper n'est pas appelé
		assertNotNull(result);
		assertTrue(result.isEmpty());
		Mockito.verify(competenceRepository, Mockito.times(1)).findAll();
		Mockito.verifyNoInteractions(competenceMapper);
	}
	//#endregion getCompetenceList

	//#region deleteCompetence
	@Test
	void test_deleteCompetence_nominal() {
		// Arrange : Créer une entité existante
		Competence competence = new Competence(1L, "Force", "FOR", "Description", "Base10", "Base13", "Base16", "Base20");
		Mockito.when(competenceRepository.findById(1L)).thenReturn(Optional.of(competence));

		// Action : Appeler la méthode
		testedClasse.deleteCompetence(1L);

		// Assert : Vérifiez que l'entité a été récupérée et supprimée
		Mockito.verify(competenceRepository, Mockito.times(1)).findById(1L);
		Mockito.verify(competenceRepository, Mockito.times(1)).delete(competence);
	}

	@Test
	void test_deleteCompetence_idNull() {
		// Action et Assert : Vérifiez si une exception est levée correctement
		Exception exception = assertThrows(WitcherToolkitExeption.class,
				() -> testedClasse.deleteCompetence(null));

		assertEquals("L'ID de la competence est null.", exception.getMessage());
	}

	@Test
	void test_deleteCompetence_notFound() {
		// Arrange : Simuler un ID qui n'existe pas dans la base
		Mockito.when(competenceRepository.findById(999L)).thenReturn(Optional.empty());

		// Action et Assert : Vérifiez si une exception est levée correctement
		Exception exception = assertThrows(WitcherToolkitExeption.class,
				() -> testedClasse.deleteCompetence(999L));

		assertEquals("La competence avec l'ID 999 n'existe pas.", exception.getMessage());
	}
	//#endregion deleteCompetence
}
