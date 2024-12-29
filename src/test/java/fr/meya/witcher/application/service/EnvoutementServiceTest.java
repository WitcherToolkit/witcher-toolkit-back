package fr.meya.witcher.application.service;

import fr.meya.witcher.application.mapper.EnvoutementMapper;
import fr.meya.witcher.common.utils.ValidationUtils;
import fr.meya.witcher.domain.model.persistent.Envoutement;
import fr.meya.witcher.exeption.WitcherToolkitExeption;
import fr.meya.witcher.infrastructure.adapter.out.IEnvoutementRepository;
import fr.meya.witcher.message.response.EnvoutementVolatile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class EnvoutementServiceTest {

    @Mock
    private IEnvoutementRepository envoutementRepository;

    @Mock
    private EnvoutementMapper envoutementMapper;

    @Mock
    private ValidationUtils validationUtils;

    @InjectMocks
    private EnvoutementService testedClasse;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    //#region isValid
    @Test
    void test_isValid_nominalCase() {
        // Arrange
        EnvoutementVolatile validEnvoutement = new EnvoutementVolatile("Nom", "10", "Effet", "Prerequis", "Danger");

        // Act
        boolean result = testedClasse.isValid(validEnvoutement);

        // Assert
        assertTrue(result);
    }

    @Test
    void test_creatEnvoutement_invaliEnvoutement() {
        // Arrange : Création d'un objet invalide (nom vide)
        EnvoutementVolatile invalidEnvoutement = new EnvoutementVolatile("", "10", "Effet", "Prerequis", "Danger");

        // Mock du message de validation
        Mockito.doThrow(new WitcherToolkitExeption("error.validation.generic"))
                .when(validationUtils).validateWithRules(Mockito.eq(invalidEnvoutement), Mockito.anyMap());

        // Action et Assert : Vérifiez qu'une exception est levée
        Exception exception = assertThrows(WitcherToolkitExeption.class,
                () -> testedClasse.createEnvoutement(invalidEnvoutement));

        assertEquals("error.validation.generic", exception.getMessage());
    }
    //#endregion isValid

    //#region createEnvoutement
    @Test
    void test_creatEnvoutement_nominal() {
        // Arrange : Création d'un objet valide
        Envoutement mappeEnvoutement = new Envoutement(1L, "Nom", "10", "Effet", "Prerequis", "Danger", null);
        EnvoutementVolatile envoutementVolatile = new EnvoutementVolatile("Nom", "10", "Effet", "Prerequis", "Danger");
        Envoutement saveEnvoutement = new Envoutement(1L, "Nom", "10", "Effet", "Prerequis", "Danger", null);

        Mockito.when(envoutementMapper.toEnvoutementEntity(envoutementVolatile)).thenReturn(mappeEnvoutement);
        Mockito.when(envoutementRepository.save(Mockito.any(Envoutement.class))).thenReturn(saveEnvoutement);

        // Action : Appeler la méthode à tester
        Envoutement result = testedClasse.createEnvoutement(envoutementVolatile);

        // Assert : Vérifiez que la méthode retourne un objet valide
        assertNotNull(result);
        assertEquals(saveEnvoutement.getNom(), result.getNom());

        Mockito.verify(envoutementMapper, Mockito.times(1)).toEnvoutementEntity(envoutementVolatile);
        Mockito.verify(envoutementRepository, Mockito.times(1)).save(mappeEnvoutement);
    }

    @Test
    void test_isValid_nulEnvoutement() {
        // Arrange : aucun objet (null)

        // Action et Assert : Vérifiez qu'une exception est levée
        Exception exception = assertThrows(WitcherToolkitExeption.class,
                () -> testedClasse.isValid(null));

        // Vérifiez le message de l'exception
        assertEquals("error.envoutement.null", exception.getMessage());
    }

    @Test
    void test_createEnvoutement_verifyMapping() {
        // Arrange : Création d'un objet de test valide
        EnvoutementVolatile envoutementVolatile = new EnvoutementVolatile("Nom", "10", "Effet", "Prerequis", "Danger");
        Envoutement mappeEnvoutement = new Envoutement(1L, "Nom", "10", "Effet", "Prerequis", "Danger", null);

        Mockito.when(envoutementMapper.toEnvoutementEntity(envoutementVolatile)).thenReturn(mappeEnvoutement);
        Mockito.when(envoutementRepository.save(Mockito.any(Envoutement.class))).thenReturn(mappeEnvoutement);

        // Action : Appeler la méthode à tester
        Envoutement result = testedClasse.createEnvoutement(envoutementVolatile);

        // Assert : Vérifiez que le mapping est correct
        assertEquals(envoutementVolatile.getNom(), result.getNom());

        Mockito.verify(envoutementMapper, Mockito.times(1)).toEnvoutementEntity(envoutementVolatile);
    }

    @Test
    void test_creatEnvoutement_invalidDataThrowsException() {
        // Arrange : Créer un objet invalide
        EnvoutementVolatile invaliEnvoutement = new EnvoutementVolatile("", "10", "Effet", "Prerequis", "Danger");

        // Simuler un retour "false" pour `isValid`
        Mockito.doThrow(new WitcherToolkitExeption("error.validation.generic"))
                .when(validationUtils).validateWithRules(Mockito.eq(invaliEnvoutement), Mockito.anyMap());

        // Action et Assert : Vérifiez que l'exception est bien levée
        Exception exception = assertThrows(WitcherToolkitExeption.class,
                () -> testedClasse.createEnvoutement(invaliEnvoutement));

        assertEquals("error.validation.generic", exception.getMessage());
    }
    //#endregion createEnvoutement

    //#region getEnvoutement
    @Test
    void test_getEnvoutement_nominal() {
        Envoutement envoutement = new Envoutement(1L, "Nom", "10", "Effet", "Prerequis", "Danger", new ArrayList<>());

        Mockito.when(envoutementRepository.findById(1L)).thenReturn(Optional.of(envoutement));

        Envoutement result = testedClasse.getEnvoutement(1L);

        assertNotNull(result);
        assertEquals("Nom", result.getNom());
    }

    @Test
    void test_getEnvoutement_idNullThrowsException() {
        Exception exception = assertThrows(WitcherToolkitExeption.class,
                () -> testedClasse.getEnvoutement(null));

        assertEquals("L'ID de l'envoûtement est null.", exception.getMessage());
    }

    @Test
    void test_getEnvoutement_notFound() {
        Mockito.when(envoutementRepository.findById(999L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(WitcherToolkitExeption.class,
                () -> testedClasse.getEnvoutement(999L));

        assertEquals("L'envoûtement avec l'ID 999 n'existe pas.", exception.getMessage());
    }
    //#endregion getEnvoutement

    //#region getEnvoutementList
    @Test
    void test_getEnvoutementList_empty() {
        // Arrange : Simuler une base vide
        Mockito.when(envoutementRepository.findAll()).thenReturn(Collections.emptyList());

        // Action : Appeler la méthode
        List<EnvoutementVolatile> result = testedClasse.getEnvoutementList();

        // Assert : Vérifiez qu'aucune erreur n'est levée et que le mapper n'est pas appelé
        assertNotNull(result);
        assertTrue(result.isEmpty());
        Mockito.verify(envoutementRepository, Mockito.times(1)).findAll();
        Mockito.verifyNoInteractions(envoutementMapper);
    }
    //#endregion getEnvoutementList

    //#region updateEnvoutement
    @Test
    void test_updateEnvoutement_nominalUpdate() {
        Envoutement existingEnvoutement = new Envoutement(1L, "Nom", "10", "Effet", "Prerequis", "Danger", new ArrayList<>());
        Envoutement updatedEnvoutement = new Envoutement(1L, "NomModifie", "15", "NouvelEffet", "Prerequis", "Danger", new ArrayList<>());

        EnvoutementVolatile updateData = new EnvoutementVolatile("NomModifie", "15", "NouvelEffet", null, null);

        Mockito.when(envoutementRepository.findById(1L)).thenReturn(Optional.of(existingEnvoutement));
        Mockito.when(envoutementRepository.save(Mockito.any(Envoutement.class))).thenReturn(updatedEnvoutement);

        Envoutement result = testedClasse.updateEnvoutement(1L, updateData);

        assertNotNull(result);
        assertEquals("NomModifie", result.getNom());
        assertEquals("15", result.getCout());
        assertEquals("NouvelEffet", result.getEffet());
    }

    @Test
    void test_updateEnvoutement_keepExistingNomWhenNull() {
        // Arrange
        Envoutement existingEnvoutement = new Envoutement(1L, "AncienNom", "10", "Effet", "Prerequis", "Danger", new ArrayList<>());
        EnvoutementVolatile updateData = new EnvoutementVolatile(null, "15", "NouvelEffet", null, null);

        Mockito.when(envoutementRepository.findById(1L)).thenReturn(Optional.of(existingEnvoutement));
        Mockito.when(envoutementRepository.save(Mockito.any(Envoutement.class))).thenReturn(existingEnvoutement);

        // Act
        Envoutement result = testedClasse.updateEnvoutement(1L, updateData);

        // Assert
        assertNotNull(result);
        assertEquals("AncienNom", result.getNom());
    }

    @Test
    void test_updateEnvoutement_keepExistingCoutWhenNull() {
        // Arrange
        Envoutement existingEnvoutement = new Envoutement(1L, "Nom", "AncienCout", "Effet", "Prerequis", "Danger", new ArrayList<>());
        EnvoutementVolatile updateData = new EnvoutementVolatile("Nom", null, "NouvelEffet", null, null);

        Mockito.when(envoutementRepository.findById(1L)).thenReturn(Optional.of(existingEnvoutement));
        Mockito.when(envoutementRepository.save(Mockito.any(Envoutement.class))).thenReturn(existingEnvoutement);

        // Act
        Envoutement result = testedClasse.updateEnvoutement(1L, updateData);

        // Assert
        assertNotNull(result);
        assertEquals("AncienCout", result.getCout());
    }

    @Test
    void test_updateEnvoutement_keepExistingEffetWhenNull() {
        // Arrange
        Envoutement existingEnvoutement = new Envoutement(1L, "Nom", "10", "AncienEffet", "Prerequis", "Danger", new ArrayList<>());
        EnvoutementVolatile updateData = new EnvoutementVolatile("Nom", "15", null, null, null);

        Mockito.when(envoutementRepository.findById(1L)).thenReturn(Optional.of(existingEnvoutement));
        Mockito.when(envoutementRepository.save(Mockito.any(Envoutement.class))).thenReturn(existingEnvoutement);

        // Act
        Envoutement result = testedClasse.updateEnvoutement(1L, updateData);

        // Assert
        assertNotNull(result);
        assertEquals("AncienEffet", result.getEffet());
    }
    //#endregion updateEnvoutement

    //#region deleteEnvoutement
    @Test
    void test_deleteEnvoutement_nominal() {
        // Arrange : Créer une entité existante
        Envoutement envoutement = new Envoutement(1L,"Nom", "10", "Effet", "Prerequis", "Danger", null);
        Mockito.when(envoutementRepository.findById(1L)).thenReturn(Optional.of(envoutement));

        // Action : Appeler la méthode
        testedClasse.deleteEnvoutement(1L);

        // Assert : Vérifiez que l'entité a été récupérée et supprimée
        Mockito.verify(envoutementRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(envoutementRepository, Mockito.times(1)).delete(envoutement);
    }

    @Test
    void test_deleteEnvoutement_idNull() {
        // Action et Assert : Vérifiez si une exception est levée correctement
        Exception exception = assertThrows(WitcherToolkitExeption.class,
                () -> testedClasse.deleteEnvoutement(null));

        assertEquals("L'ID de l'envoûtement est null.", exception.getMessage());
    }

    @Test
    void test_deleteEnvoutement_notFound() {
        // Arrange : Simuler un ID qui n'existe pas dans la base
        Mockito.when(envoutementRepository.findById(999L)).thenReturn(Optional.empty());

        // Action et Assert : Vérifiez si une exception est levée correctement
        Exception exception = assertThrows(WitcherToolkitExeption.class,
                () -> testedClasse.deleteEnvoutement(999L));

        assertEquals("L'envoûtement avec l'ID 999 n'existe pas.", exception.getMessage());
    }
    //#endregion deleteEnvoutement


}

