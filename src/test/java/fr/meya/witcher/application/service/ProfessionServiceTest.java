package fr.meya.witcher.application.service;

import fr.meya.witcher.domain.model.persistent.CompetenceSpecifique;
import fr.meya.witcher.domain.model.persistent.Profession;
import fr.meya.witcher.infrastructure.adapter.out.IProfessionRepository;
import fr.meya.witcher.message.response.ProfessionVolatile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class ProfessionServiceTest {

	@Mock
	private IProfessionRepository professionRepository;

	@InjectMocks
	private ProfessionService testedClasse;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void test_getListeDesProfessionsVolatiles_renvois_une_liste_de_profession() {

		List<Profession> professionList = new ArrayList<>();

		CompetenceSpecifique competenceSpecifique1 = new CompetenceSpecifique();
		competenceSpecifique1.setIdCompetenceSpecifique(1L);
		competenceSpecifique1.setNom("Compétence1");
		competenceSpecifique1.setDescription("Description pour Compétence1");
		competenceSpecifique1.setCodeCaracteristique("CHA");
		competenceSpecifique1.setSpecialisation("Spécialisation1");
		competenceSpecifique1.setPrerequis("Prérequis1");

		CompetenceSpecifique competenceSpecifique2 = new CompetenceSpecifique();
		competenceSpecifique2.setIdCompetenceSpecifique(2L);
		competenceSpecifique2.setNom("Compétence2");
		competenceSpecifique2.setDescription("Description pour Compétence2");
		competenceSpecifique2.setCodeCaracteristique("COR");
		competenceSpecifique2.setSpecialisation("Spécialisation2");
		competenceSpecifique2.setPrerequis("Prérequis2");

		Profession profession1 = new Profession();
		profession1.setIdProfession(1L);
		profession1.setNom("Profession1");
		profession1.setCompetenceExclusive("false");
		profession1.setDescription("Description1");
		profession1.setCodeCaracteristique("INT");
		profession1.setCompetenceSpecifique(Arrays.asList(competenceSpecifique1, competenceSpecifique2)); // Associez les compétences
		professionList.add(profession1);

		Profession profession2 = new Profession();
		profession2.setIdProfession(2L);
		profession2.setNom("Profession2");
		profession2.setCompetenceExclusive("true");
		profession2.setDescription("Description2");
		profession2.setCodeCaracteristique("INT");
		profession2.setCompetenceSpecifique(Collections.emptyList()); // Pas de compétences ici
		professionList.add(profession2);

		//Mockito.when(professionRepository.getListeDesProfessionsAvecCompetences()).thenReturn(professionList);
		Mockito.when(professionRepository.findAll()).thenReturn(professionList);

		List<ProfessionVolatile> result = testedClasse.getListeDesProfessionsVolatiles();

		Assertions.assertNotNull(result);
		Assertions.assertEquals(2, result.size());
		Assertions.assertEquals("Profession1", result.get(0).getNom());
		Assertions.assertEquals("Profession2", result.get(1).getNom());

		//Mockito.verify(professionRepository, Mockito.times(1)).getListeDesProfessionsAvecCompetences();
		Mockito.verify(professionRepository, Mockito.times(1)).findAll();

	}

}
