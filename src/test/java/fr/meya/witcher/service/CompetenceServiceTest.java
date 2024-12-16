package fr.meya.witcher.service;

import fr.meya.witcher.adapter.port.out.ICompetenceRepository;
import fr.meya.witcher.model.persistent.Competence;
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

class CompetenceServiceTest {

	@Mock
	private ICompetenceRepository competenceRepository;

	@InjectMocks
	private CompetenceService competenceService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void test_findAll_renvois_une_liste_de_caracteristique() {

		List<Competence> competenceList = new ArrayList<>();

		Competence competence1 = new Competence();
		competence1.setIdCompetence(1L);
		competence1.setNom("Bagarre");
		competence1.setCodeCaracteristique("FOR");
		competence1.setDescription("Lorem ipsum");
		competence1.setDescriptionBase10("Lorem ipsum base 10");
		competence1.setDescriptionBase13("Lorem ipsum base 13");
		competence1.setDescriptionBase16("Lorem ipsum base 16");
		competence1.setDescriptionBase20("Lorem ipsum base 20");
		competenceList.add(competence1);

		Competence competence2 = new Competence();
		competence2.setNom("Arbalète");
		competence2.setCodeCaracteristique("DEX");
		competence2.setDescription("Lorem ipsum dolor");
		competence2.setDescriptionBase10("Lorem ipsum base 10");
		competence2.setDescriptionBase13("Lorem ipsum base 13");
		competence2.setDescriptionBase16("Lorem ipsum base 16");
		competence2.setDescriptionBase20("Lorem ipsum base 20");
		competenceList.add(competence2);

		Mockito.when(competenceRepository.findAll()).thenReturn(competenceList);

		List<Competence> result = competenceService.getCompetenceList();

		Mockito.verify(competenceRepository).findAll();

		assertNotNull(result);
		assertEquals(2, result.size());
		assertEquals("Bagarre", result.get(0).getNom());
		assertEquals("Arbalète", result.get(1).getNom());

		Mockito.verify(competenceRepository, Mockito.times(1)).findAll();
	}

}
