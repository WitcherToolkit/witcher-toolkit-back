package fr.meya.witcher.message.response;

import fr.meya.witcher.domain.model.persistent.Caracteristique;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompetenceVolatile {

	private String nom;

	private String codeCaracteristique;


	private String description;

	private String descriptionBase10;


	private String descriptionBase13;

	private String descriptionBase16;


	private String descriptionBase20;

	private String specialisation;

	private boolean isExclusive;

	private CaracteristiqueVolatile caracteristique;

	private List<CompetenceProfessionVolatile> ProfessionList;


}
