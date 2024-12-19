package fr.meya.witcher.application.service;

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
    void test_isValid_nullObject() {
        Exception exception = assertThrows(WitcherToolkitExeption.class,
                () -> testedClasse.isValid(null));
        assertEquals("Aucun envoutement fournie.", exception.getMessage());
    }

    @Test
    void test_isValid_nomVide() {
        EnvoutementVolatile invalidEnvoutement = new EnvoutementVolatile("", "10", "Effet", "Prerequis", "Danger");

        Exception exception = assertThrows(WitcherToolkitExeption.class,
                () -> testedClasse.isValid(invalidEnvoutement));

        assertEquals("Le nom de l'envoutement est obligatoire.", exception.getMessage());
    }

    @Test
    void test_isValid_nomTooLong() {
        EnvoutementVolatile invalidEnvoutement = new EnvoutementVolatile("NomDePlusDeSeizeCaracteres", "10", "Effet", "Prerequis", "Danger");

        Exception exception = assertThrows(WitcherToolkitExeption.class,
                () -> testedClasse.isValid(invalidEnvoutement));

        assertEquals("Le nom de l'envoutement doit faire maximum 16 caractères.", exception.getMessage());
    }

    @Test
    void test_isValid_coutTooLong() {
        EnvoutementVolatile invalidEnvoutement = new EnvoutementVolatile("Nom", "CoutDePlusDeDixCaracteres", "Effet", "Prerequis", "Danger");

        Exception exception = assertThrows(WitcherToolkitExeption.class,
                () -> testedClasse.isValid(invalidEnvoutement));

        assertEquals("Le coût de l'envoutement doit faire maximum 16 caractères.", exception.getMessage());
    }

    @Test
    void test_isValid_dangerTooLong() {
        EnvoutementVolatile invalidEnvoutement = new EnvoutementVolatile("Nom", "10", "Effet", "Prerequis", "UnDangerTropLong");

        Exception exception = assertThrows(WitcherToolkitExeption.class,
                () -> testedClasse.isValid(invalidEnvoutement));

        assertEquals("Le danger de l'envoutement doit faire maximum 6 caractères.", exception.getMessage());
    }

    @Test
    void test_isValid_coutNull() {
        EnvoutementVolatile invalidEnvoutement = new EnvoutementVolatile("Nom", null, "Effet", "Prerequis", "Danger");

        Exception exception = assertThrows(WitcherToolkitExeption.class,
                () -> testedClasse.isValid(invalidEnvoutement));

        assertEquals("Le coût de l'envoutement est obligatoire.", exception.getMessage());
    }

    @Test
    void test_isValid_coutVide() {
        EnvoutementVolatile invalidEnvoutement = new EnvoutementVolatile("Nom", "", "Effet", "Prerequis", "Danger");

        Exception exception = assertThrows(WitcherToolkitExeption.class,
                () -> testedClasse.isValid(invalidEnvoutement));

        assertEquals("Le coût de l'envoutement est obligatoire.", exception.getMessage());
    }

    @Test
    void test_isValid_effetNull() {
        EnvoutementVolatile invalidEnvoutement = new EnvoutementVolatile("Nom", "10", null, "Prerequis", "Danger");

        Exception exception = assertThrows(WitcherToolkitExeption.class,
                () -> testedClasse.isValid(invalidEnvoutement));

        assertEquals("L'effet de l'envoutement est obligatoire.", exception.getMessage());
    }

    @Test
    void test_isValid_effetVide() {
        EnvoutementVolatile invalidEnvoutement = new EnvoutementVolatile("Nom", "10", "", "Prerequis", "Danger");

        Exception exception = assertThrows(WitcherToolkitExeption.class,
                () -> testedClasse.isValid(invalidEnvoutement));

        assertEquals("L'effet de l'envoutement est obligatoire.", exception.getMessage());
    }

    @Test
    void test_isValid_prerequisNull() {
        EnvoutementVolatile invalidEnvoutement = new EnvoutementVolatile("Nom", "10", "Effet", null, "Danger");

        Exception exception = assertThrows(WitcherToolkitExeption.class,
                () -> testedClasse.isValid(invalidEnvoutement));

        assertEquals("Le pre-requis de l'envoutement est obligatoire.", exception.getMessage());
    }

    @Test
    void test_isValid_prerequisVide() {
        EnvoutementVolatile invalidEnvoutement = new EnvoutementVolatile("Nom", "10", "Effet", "", "Danger");

        Exception exception = assertThrows(WitcherToolkitExeption.class,
                () -> testedClasse.isValid(invalidEnvoutement));

        assertEquals("Le pre-requis de l'envoutement est obligatoire.", exception.getMessage());
    }

    @Test
    void test_isValid_dangerNull() {
        EnvoutementVolatile invalidEnvoutement = new EnvoutementVolatile("Nom", "10", "Effet", "Prerequis", null);

        Exception exception = assertThrows(WitcherToolkitExeption.class,
                () -> testedClasse.isValid(invalidEnvoutement));

        assertEquals("Le danger de l'envoutement est obligatoire.", exception.getMessage());
    }

    @Test
    void test_isValid_dangerVide() {
        EnvoutementVolatile invalidEnvoutement = new EnvoutementVolatile("Nom", "10", "Effet", "Prerequis", "");

        Exception exception = assertThrows(WitcherToolkitExeption.class,
                () -> testedClasse.isValid(invalidEnvoutement));

        assertEquals("Le danger de l'envoutement est obligatoire.", exception.getMessage());
    }

    //#endregion isValid

    //#region createEnvoutement
    @Test
    void test_createEnvoutement_nominal() {
        // Arrange : création d'un objet EnvoutementVolatile valide
        EnvoutementVolatile envoutementVolatile = new EnvoutementVolatile("Nom", "10", "Effet", "Prerequis", "Danger");

        // Création d'un objet Envoutement avec une liste vide pour la simulation de save
        Envoutement savedEnvoutement = new Envoutement(1L, "Nom", "10", "Effet", "Prerequis", "Danger", new ArrayList<>());

        // Simulation du comportement du repository
        Mockito.when(envoutementRepository.save(Mockito.any(Envoutement.class))).thenReturn(savedEnvoutement);

        // Act : exécution de la méthode à tester
        Envoutement result = testedClasse.createEnvoutement(envoutementVolatile);

        // Assert : vérifications sur le résultat
        assertNotNull(result);
        assertEquals("Nom", result.getNom());
        assertEquals("10", result.getCout());
        assertEquals("Effet", result.getEffet());
        assertEquals("Prerequis", result.getPrerequis());
        assertEquals("Danger", result.getDanger());
    }

    @Test
    void test_createEnvoutement_invalidEnvoutement() {
        EnvoutementVolatile invalidEnvoutement = new EnvoutementVolatile(null, "10", "Effet", "Prerequis", "Danger");

        Exception exception = assertThrows(WitcherToolkitExeption.class,
                () -> testedClasse.createEnvoutement(invalidEnvoutement));

        assertEquals("Le nom de l'envoutement est obligatoire.", exception.getMessage());
    }

    @Test
    void test_createEnvoutement_invalidByIsValid() {
        // Arrange: création d'un objet EnvoutementVolatile invalide (nom vide)
        EnvoutementVolatile invalidEnvoutement = new EnvoutementVolatile("", "", "", "", "");

        // Simuler un retour "false" pour `isValid`
        EnvoutementService serviceMock = Mockito.spy(testedClasse);
        Mockito.doReturn(false).when(serviceMock).isValid(invalidEnvoutement);

        // Action et Assert : Vérifiez que l'exception est bien levée
        Exception exception = assertThrows(WitcherToolkitExeption.class,
                () -> serviceMock.createEnvoutement(invalidEnvoutement));

        assertEquals("Les informations de l'envoûtement ne sont pas valides.", exception.getMessage());
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
    void test_updateEnvoutement_idNullThrowsException() {
        Exception exception = assertThrows(WitcherToolkitExeption.class,
                () -> testedClasse.updateEnvoutement(null, new EnvoutementVolatile()));

        assertEquals("L'ID de la competence est null.", exception.getMessage());
    }

    @Test
    void test_updateEnvoutement_invalidDataThrowsException() {
        Mockito.when(envoutementRepository.findById(1L)).thenReturn(Optional.of(new Envoutement()));

        Exception exception = assertThrows(WitcherToolkitExeption.class,
                () -> testedClasse.updateEnvoutement(1L, null));

        assertEquals("Les données de mise à jour sont nulles.", exception.getMessage());
    }

    @Test
    void test_updateEnvoutement_updatePrerequis() {
        // Arrange
        Envoutement existingEnvoutement = new Envoutement(1L, "Nom", "10", "Effet", "AncienPrerequis", "Danger", new ArrayList<>());
        EnvoutementVolatile updateData = new EnvoutementVolatile(null, null, null, "NouveauPrerequis", null);

        Mockito.when(envoutementRepository.findById(1L)).thenReturn(Optional.of(existingEnvoutement));
        Mockito.when(envoutementRepository.save(Mockito.any(Envoutement.class))).thenReturn(existingEnvoutement);

        // Act
        Envoutement result = testedClasse.updateEnvoutement(1L, updateData);

        // Assert
        assertNotNull(result);
        assertEquals("NouveauPrerequis", result.getPrerequis());
    }

    @Test
    void test_updateEnvoutement_updateDanger() {
        // Arrange
        Envoutement existingEnvoutement = new Envoutement(1L, "Nom", "10", "Effet", "Prerequis", "AncienDanger", new ArrayList<>());
        EnvoutementVolatile updateData = new EnvoutementVolatile(null, null, null, null, "NouveauDanger");

        Mockito.when(envoutementRepository.findById(1L)).thenReturn(Optional.of(existingEnvoutement));
        Mockito.when(envoutementRepository.save(Mockito.any(Envoutement.class))).thenReturn(existingEnvoutement);

        // Act
        Envoutement result = testedClasse.updateEnvoutement(1L, updateData);

        // Assert
        assertNotNull(result);
        assertEquals("NouveauDanger", result.getDanger());
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
        Envoutement envoutement = new Envoutement(1L, "Nom", "10", "Effet", "Prerequis", "Danger", new ArrayList<>());

        Mockito.when(envoutementRepository.findById(1L)).thenReturn(Optional.of(envoutement));

        Envoutement result = testedClasse.deleteEnvoutement(1L);

        Mockito.verify(envoutementRepository).delete(envoutement);
        assertEquals(envoutement, result);
    }

    @Test
    void test_deleteEnvoutement_notFound() {
        Mockito.when(envoutementRepository.findById(999L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(WitcherToolkitExeption.class,
                () -> testedClasse.deleteEnvoutement(999L));

        assertEquals("L'envoûtement avec l'ID 999 n'existe pas.", exception.getMessage());
    }
    //#endregion deleteEnvoutement

    //#region getEnvoutementList
    @Test
    void test_getEnvoutementList_nominal() {
        List<Envoutement> envoutements = List.of(
                new Envoutement(1L, "Nom1", "10", "Effet1", "Prerequis1", "Danger1", new ArrayList<>()),
                new Envoutement(2L, "Nom2", "20", "Effet2", "Prerequis2", "Danger2", new ArrayList<>())
        );

        Mockito.when(envoutementRepository.findAll()).thenReturn(envoutements);

        List<EnvoutementVolatile> result = testedClasse.getEnvoutementList();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Nom1", result.get(0).getNom());
    }

    @Test
    void test_getEnvoutementList_empty() {
        Mockito.when(envoutementRepository.findAll()).thenReturn(Collections.emptyList());

        List<EnvoutementVolatile> result = testedClasse.getEnvoutementList();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
    //#endregion getEnvoutementList
}

