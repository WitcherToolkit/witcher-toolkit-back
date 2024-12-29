package fr.meya.witcher.application.service;

import fr.meya.witcher.application.mapper.ProfessionMapper;
import fr.meya.witcher.common.utils.ValidationUtils;
import fr.meya.witcher.domain.model.persistent.Profession;
import fr.meya.witcher.exeption.WitcherToolkitExeption;
import fr.meya.witcher.infrastructure.adapter.out.IProfessionRepository;
import fr.meya.witcher.message.response.ProfessionVolatile;
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

class ProfessionServiceTest {

	@Mock
	private IProfessionRepository professionRepository;

	@Mock
	private ProfessionMapper professionMapper;

	@Mock
	private ValidationUtils validationUtils;

	@InjectMocks
	private ProfessionService testedClasse;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	//#region isValid
	@Test
	void test_isValid_nominalCase() {
		// Arrange : Création d'un objet valide
		ProfessionVolatile validProfession = new ProfessionVolatile("Nom", "CempetenceExclusive", "Description", "Code");

		// Action : Appeler la méthode à tester
		boolean result = testedClasse.isValid(validProfession);

		// Assert : Vérifiez que la méthode retourne true
		assertTrue(result);
	}

	@Test
	void test_isValid_nullProfession() {
		// Arrange : aucun objet (null)

		// Action et Assert : Vérifiez qu'une exception est levée
		Exception exception = assertThrows(WitcherToolkitExeption.class,
				() -> testedClasse.isValid(null));

		// Vérifiez le message de l'exception
		assertEquals("error.profession.null", exception.getMessage());
	}
	//#endregion isValid

	//#region createMProfession
	@Test
	void test_createProfession_nominal() {
		// Arrange : Création d'un objet valide
		Profession mappedProfession = new Profession(1L, "Nom", "5 END", "Effet", "3m", null);
		ProfessionVolatile professionVolatile = new ProfessionVolatile("Nom", "5 END", "Effet", "3m");
		Profession savedProfession = new Profession(1L, "Nom", "5 END", "Effet", "3m", null);

		Mockito.when(professionMapper.toProfessionEntity(professionVolatile)).thenReturn(mappedProfession);
		Mockito.when(professionRepository.save(Mockito.any(Profession.class))).thenReturn(savedProfession);

		// Action : Appeler la méthode à tester
		Profession result = testedClasse.createProfession(professionVolatile);

		// Assert : Vérifiez que la méthode retourne un objet valide
		assertNotNull(result);
		assertEquals(savedProfession.getNom(), result.getNom());

		Mockito.verify(professionMapper, Mockito.times(1)).toProfessionEntity(professionVolatile);
		Mockito.verify(professionRepository, Mockito.times(1)).save(mappedProfession);
	}

	@Test
	void test_createProfession_invalidProfession() {
		// Arrange : Création d'un objet invalide (nom vide)
		ProfessionVolatile invalidProfession = new ProfessionVolatile("", "5 END", "Effet", "3m");

		// Mock du message de validation
		Mockito.doThrow(new WitcherToolkitExeption("error.validation.invalid"))
				.when(validationUtils).validateWithRules(Mockito.eq(invalidProfession), Mockito.anyMap());

		// Action et Assert : Vérifiez qu'une exception est levée
		Exception exception = assertThrows(WitcherToolkitExeption.class,
				() -> testedClasse.createProfession(invalidProfession));

		assertEquals("error.validation.generic", exception.getMessage());
	}

	@Test
	void test_createProfession_verifyMapping() {
		// Arrange : Création d'un objet de test valide
		ProfessionVolatile professionVolatile = new ProfessionVolatile("Nom", "5 END", "Effet", "3m");
		Profession mappedProfession = new Profession(1L, "Nom", "5 END", "Effet", "3m", null);

		Mockito.when(professionMapper.toProfessionEntity(professionVolatile)).thenReturn(mappedProfession);
		Mockito.when(professionRepository.save(Mockito.any(Profession.class))).thenReturn(mappedProfession);

		// Action : Appeler la méthode à tester
		Profession result = testedClasse.createProfession(professionVolatile);

		// Assert : Vérifiez que le mapping est correct
		assertEquals(professionVolatile.getNom(), result.getNom());

		Mockito.verify(professionMapper, Mockito.times(1)).toProfessionEntity(professionVolatile);
	}

	@Test
	void test_createProfession_invalidDataThrowsException() {
		// Arrange : Créer un objet invalide
		ProfessionVolatile invalidProfession = new ProfessionVolatile("", "5 END", "Effet", "3m");

		// Simuler un retour "false" pour `isValid`
		Mockito.doThrow(new WitcherToolkitExeption("error.validation.generic"))
				.when(validationUtils).validateWithRules(Mockito.eq(invalidProfession), Mockito.anyMap());

		// Action et Assert : Vérifiez que l'exception est bien levée
		Exception exception = assertThrows(WitcherToolkitExeption.class,
				() -> testedClasse.createProfession(invalidProfession));

		assertEquals("error.validation.generic", exception.getMessage());
	}
	//#endregion createProfession

	//#region getProfession
	@Test
	void test_getProfession_nominal() {
		Profession profession = new Profession();
		profession.setIdProfession(1L);
		profession.setNom("Force");

		Mockito.when(professionRepository.findById(1L)).thenReturn(Optional.of(profession));

		Profession result = testedClasse.getProfession(1L);

		assertNotNull(result);
		assertEquals("Force", result.getNom());
	}

	@Test
	void test_getProfession_idNull() {
		Exception exception = assertThrows(WitcherToolkitExeption.class, () -> testedClasse.getProfession(null));
		assertEquals("L'ID de la profession est null.", exception.getMessage());
	}

	@Test
	void test_getProfession_nonExistant() {
		Mockito.when(professionRepository.findById(999L)).thenReturn(Optional.empty());

		Exception exception = assertThrows(WitcherToolkitExeption.class, () -> testedClasse.getProfession(999L));
		assertEquals("La profession avec l'ID 999 n'existe pas.", exception.getMessage());
	}
	//#endregion getProfession

	//#region getProfessionList
	@Test
	void test_getProfessionList_empty() {
		// Arrange : Simuler une base vide
		Mockito.when(professionRepository.findAll()).thenReturn(Collections.emptyList());

		// Action : Appeler la méthode
		List<ProfessionVolatile> result = testedClasse.getProfessionList();

		// Assert : Vérifiez qu'aucune erreur n'est levée et que le mapper n'est pas appelé
		assertNotNull(result);
		assertTrue(result.isEmpty());
		Mockito.verify(professionRepository, Mockito.times(1)).findAll();
		Mockito.verifyNoInteractions(professionMapper);
	}
	//#endregion getProfessionList

	//#region updateProfession
	@Test
	void test_updateProfession_partialUpdate() {
		Profession existingProfession = new Profession(1L, "Nom", "5 END", "Effet", "3m", null);
		Profession updatedProfession = new Profession(1L, "Nouveau nom", "5 END", "Effet", "3m", null);

		ProfessionVolatile updateData = new ProfessionVolatile("Nouveau nom", null, null, null);

		Mockito.when(professionRepository.findById(1L)).thenReturn(Optional.of(existingProfession));
		Mockito.when(professionRepository.save(Mockito.any(Profession.class))).thenReturn(updatedProfession);

		Profession result = testedClasse.updateProfession(1L, updateData);

		assertNotNull(result);
		assertEquals("Nouveau nom", result.getNom());
		assertEquals("Effet", result.getDescription()); // L'effet n'est pas modifié.
		assertEquals("3m", result.getCodeCaracteristique());
	}

	@Test
	void test_updateProfession_noFieldsUpdated() {
		Profession existingProfession = new Profession(1L, "Nom", "5 END", "Effet", "3m",  null);

		ProfessionVolatile updateData = new ProfessionVolatile(null, null, null, null);

		Mockito.when(professionRepository.findById(1L)).thenReturn(Optional.of(existingProfession));
		Mockito.when(professionRepository.save(Mockito.any(Profession.class))).then(invocation -> invocation.getArgument(0));

		Profession result = testedClasse.updateProfession(1L, updateData);

		assertNotNull(result);
		assertEquals("Nom", result.getNom());
		assertEquals("Effet", result.getDescription());
		assertEquals("3m", result.getCodeCaracteristique()); // Rien n’est modifié.
	}
	//#endregion updateProfession

	//#region deleteProfession
	@Test
	void test_deleteProfession_nominal() {
		// Arrange : Créer une entité existante
		Profession profession = new Profession(1L,"Nom", "5 END", "Effet", "3m", null);
		Mockito.when(professionRepository.findById(1L)).thenReturn(Optional.of(profession));

		// Action : Appeler la méthode
		testedClasse.deleteProfession(1L);

		// Assert : Vérifiez que l'entité a été récupérée et supprimée
		Mockito.verify(professionRepository, Mockito.times(1)).findById(1L);
		Mockito.verify(professionRepository, Mockito.times(1)).delete(profession);
	}

	@Test
	void test_deleteProfession_idNull() {
		// Action et Assert : Vérifiez si une exception est levée correctement
		Exception exception = assertThrows(WitcherToolkitExeption.class,
				() -> testedClasse.deleteProfession(null));

		assertEquals("L'ID de la profession est null.", exception.getMessage());
	}

	@Test
	void test_deleteProfession_notFound() {
		// Arrange : Simuler un ID qui n'existe pas dans la base
		Mockito.when(professionRepository.findById(999L)).thenReturn(Optional.empty());

		// Action et Assert : Vérifiez si une exception est levée correctement
		Exception exception = assertThrows(WitcherToolkitExeption.class,
				() -> testedClasse.deleteProfession(999L));

		assertEquals("La profession avec l'ID 999 n'existe pas.", exception.getMessage());
	}
	//#endregion deleteProfession



}
