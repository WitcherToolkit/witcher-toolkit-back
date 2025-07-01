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

	private String description;

	private String prerequis;

	private String specialisation;

	private boolean isExclusive;

	private CaracteristiqueVolatile caracteristique;

	private String tags;

	private List<CompetenceProfessionVolatile> ProfessionList;

}
