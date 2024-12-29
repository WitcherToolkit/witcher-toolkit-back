package fr.meya.witcher.application.service;

import fr.meya.witcher.application.mapper.MagieMapper;
import fr.meya.witcher.common.utils.ValidationUtils;
import fr.meya.witcher.domain.model.persistent.Magie;
import fr.meya.witcher.exeption.WitcherToolkitExeption;
import fr.meya.witcher.infrastructure.adapter.out.IMagieRepository;
import fr.meya.witcher.message.response.MagieVolatile;
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
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MagieServiceTest {

    @Mock
    private IMagieRepository magieRepository;

    @Mock
    private MagieMapper magieMapper;

    @Mock
    private ValidationUtils validationUtils;

    @InjectMocks
    private MagieService testedClasse;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }


    //#region isValid
    @Test
    void test_isValid_nominalCase() {
        // Arrange : Création d'un objet valide
        MagieVolatile validMagie = new MagieVolatile("Nom", "5 END", "Effet", "3m", "5 round", "Feu", "Novice", "Esquive", "Mage");

        // Action : Appeler la méthode à tester
        boolean result = testedClasse.isValid(validMagie);

        // Assert : Vérifiez que la méthode retourne true
        assertTrue(result);
    }
    //#endregion isValid

    //#region createMagie
    @Test
    void test_createMagie_nominal() {
        // Arrange : Création d'un objet valide
        Magie mappedMagie = new Magie(1L, "Nom", "5 END", "Effet", "3m", "5 round", "Feu", "Novice", "Esquive", "Mage", null);
        MagieVolatile magieVolatile = new MagieVolatile("Nom", "5 END", "Effet", "3m", "5 round", "Feu", "Novice", "Esquive", "Mage");
        Magie savedMagie = new Magie(1L, "Nom", "5 END", "Effet", "3m", "5 round", "Feu", "Novice", "Esquive", "Mage", null);

        Mockito.when(magieMapper.toMagieEntity(magieVolatile)).thenReturn(mappedMagie);
        Mockito.when(magieRepository.save(Mockito.any(Magie.class))).thenReturn(savedMagie);

        // Action : Appeler la méthode à tester
        Magie result = testedClasse.createMagie(magieVolatile);

        // Assert : Vérifiez que la méthode retourne un objet valide
        assertNotNull(result);
        assertEquals(savedMagie.getNom(), result.getNom());

        Mockito.verify(magieMapper, Mockito.times(1)).toMagieEntity(magieVolatile);
        Mockito.verify(magieRepository, Mockito.times(1)).save(mappedMagie);
    }

    @Test
    void test_createMagie_invalidMagie() {
        // Arrange : Création d'un objet invalide (nom vide)
        MagieVolatile invalidMagie = new MagieVolatile("", "5 END", "Effet", "3m", "5 round", "Feu", "Novice", "Esquive", "Mage");

        // Mock du message de validation
        Mockito.doThrow(new WitcherToolkitExeption("error.validation.generic"))
                .when(validationUtils).validateWithRules(Mockito.eq(invalidMagie), Mockito.anyMap());

        // Action et Assert : Vérifiez qu'une exception est levée
        Exception exception = assertThrows(WitcherToolkitExeption.class,
                () -> testedClasse.createMagie(invalidMagie));

        assertEquals("error.validation.generic", exception.getMessage());
    }

    @Test
    void test_isValid_nullMagie() {
        // Arrange : aucun objet (null)

        // Action et Assert : Vérifiez qu'une exception est levée
        Exception exception = assertThrows(WitcherToolkitExeption.class,
                () -> testedClasse.isValid(null));

        // Vérifiez le message de l'exception
        assertEquals("error.magie.null", exception.getMessage());
    }

    @Test
    void test_createMagie_verifyMapping() {
        // Arrange : Création d'un objet de test valide
        MagieVolatile magieVolatile = new MagieVolatile("Nom", "5 END", "Effet", "3m", "5 round", "Feu", "Novice", "Esquive", "Mage");
        Magie mappedMagie = new Magie(1L, "Nom", "5 END", "Effet", "3m", "5 round", "Feu", "Novice", "Esquive", "Mage", null);

        Mockito.when(magieMapper.toMagieEntity(magieVolatile)).thenReturn(mappedMagie);
        Mockito.when(magieRepository.save(Mockito.any(Magie.class))).thenReturn(mappedMagie);

        // Action : Appeler la méthode à tester
        Magie result = testedClasse.createMagie(magieVolatile);

        // Assert : Vérifiez que le mapping est correct
        assertEquals(magieVolatile.getNom(), result.getNom());

        Mockito.verify(magieMapper, Mockito.times(1)).toMagieEntity(magieVolatile);
    }

    @Test
    void test_createMagie_invalidDataThrowsException() {
        // Arrange : Créer un objet invalide
        MagieVolatile invalidMagie = new MagieVolatile("", "5 END", "Effet", "3m", "5 round", "Feu", "Novice", "Esquive", "Mage");

        // Simuler un retour "false" pour `isValid`
        Mockito.doThrow(new WitcherToolkitExeption("error.validation.generic"))
                .when(validationUtils).validateWithRules(Mockito.eq(invalidMagie), Mockito.anyMap());

        // Action et Assert : Vérifiez que l'exception est bien levée
        Exception exception = assertThrows(WitcherToolkitExeption.class,
                () -> testedClasse.createMagie(invalidMagie));

        assertEquals("error.validation.generic", exception.getMessage());
    }
    //#endregion createMagie

    //#region getMagie
    @Test
    void test_getMagie_nominal() {
        Magie magie = new Magie();
        magie.setIdMagie(1L);
        magie.setNom("Force");

        Mockito.when(magieRepository.findById(1L)).thenReturn(Optional.of(magie));

        Magie result = testedClasse.getMagie(1L);

        assertNotNull(result);
        assertEquals("Force", result.getNom());
    }

    @Test
    void test_getMagie_idNull() {
        Exception exception = assertThrows(WitcherToolkitExeption.class, () -> testedClasse.getMagie(null));
        assertEquals("L'ID de la magie est null.", exception.getMessage());
    }

    @Test
    void test_getMagie_nonExistant() {
        Mockito.when(magieRepository.findById(999L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(WitcherToolkitExeption.class, () -> testedClasse.getMagie(999L));
        assertEquals("La magie avec l'ID 999 n'existe pas.", exception.getMessage());
    }
    //#endregion getMagie

    //#region getMagieList
    @Test
    void test_getMagieList_empty() {
        // Arrange : Simuler une base vide
        Mockito.when(magieRepository.findAll()).thenReturn(Collections.emptyList());

        // Action : Appeler la méthode
        List<MagieVolatile> result = testedClasse.getMagieList();

        // Assert : Vérifiez qu'aucune erreur n'est levée et que le mapper n'est pas appelé
        assertNotNull(result);
        assertTrue(result.isEmpty());
        Mockito.verify(magieRepository, Mockito.times(1)).findAll();
        Mockito.verifyNoInteractions(magieMapper);
    }
    //#endregion getMagieList

    //#region updateMagie
    @Test
    void test_updateMagie_partialUpdate() {
        Magie existingMagie = new Magie(1L, "Nom", "5 END", "Effet", "3m", "5 round", "Feu", "Novice", "Esquive", "Mage", null);
        Magie updatedMagie = new Magie(1L, "Nouveau nom", "5 END", "Effet", "3m", "5 round", "Air", "Novice", "Esquive", "Mage", null);

        MagieVolatile updateData = new MagieVolatile("Nouveau nom", null, null, null, null, "Air", null, null, null);

        Mockito.when(magieRepository.findById(1L)).thenReturn(Optional.of(existingMagie));
        Mockito.when(magieRepository.save(Mockito.any(Magie.class))).thenReturn(updatedMagie);

        Magie result = testedClasse.updateMagie(1L, updateData);

        assertNotNull(result);
        assertEquals("Nouveau nom", result.getNom());
        assertEquals("Effet", result.getEffet()); // L'effet n'est pas modifié.
        assertEquals("Air", result.getElement());
    }

    @Test
    void test_updateMagie_noFieldsUpdated() {
        Magie existingMagie = new Magie(1L, "Nom", "5 END", "Effet", "3m", "5 round", "Feu", "Novice", "Esquive", "Mage", null);

        MagieVolatile updateData = new MagieVolatile(null, null, null, null, null, null, null, null, null);

        Mockito.when(magieRepository.findById(1L)).thenReturn(Optional.of(existingMagie));
        Mockito.when(magieRepository.save(Mockito.any(Magie.class))).then(invocation -> invocation.getArgument(0));

        Magie result = testedClasse.updateMagie(1L, updateData);

        assertNotNull(result);
        assertEquals("Nom", result.getNom());
        assertEquals("Effet", result.getEffet());
        assertEquals("Feu", result.getElement()); // Rien n’est modifié.
    }
    //#endregion updateMagie

    //#region deleteMagie
    @Test
    void test_deleteMagie_nominal() {
        // Arrange : Créer une entité existante
        Magie magie = new Magie(1L,"Nom", "5 END", "Effet", "3m", "5 round", "Feu", "Novice", "Esquive", "Mage", null);
        Mockito.when(magieRepository.findById(1L)).thenReturn(Optional.of(magie));

        // Action : Appeler la méthode
        testedClasse.deleteMagie(1L);

        // Assert : Vérifiez que l'entité a été récupérée et supprimée
        Mockito.verify(magieRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(magieRepository, Mockito.times(1)).delete(magie);
    }

    @Test
    void test_deleteMagie_idNull() {
        // Action et Assert : Vérifiez si une exception est levée correctement
        Exception exception = assertThrows(WitcherToolkitExeption.class,
                () -> testedClasse.deleteMagie(null));

        assertEquals("L'ID de la magie est null.", exception.getMessage());
    }

    @Test
    void test_deleteMagie_notFound() {
        // Arrange : Simuler un ID qui n'existe pas dans la base
        Mockito.when(magieRepository.findById(999L)).thenReturn(Optional.empty());

        // Action et Assert : Vérifiez si une exception est levée correctement
        Exception exception = assertThrows(WitcherToolkitExeption.class,
                () -> testedClasse.deleteMagie(999L));

        assertEquals("La magie avec l'ID 999 n'existe pas.", exception.getMessage());
    }
    //#endregion deleteMagie


}
