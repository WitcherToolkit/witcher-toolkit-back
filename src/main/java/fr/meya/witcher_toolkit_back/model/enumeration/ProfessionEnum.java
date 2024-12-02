package fr.meya.witcher_toolkit_back.model.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.EnumSet;

@AllArgsConstructor
public enum ProfessionEnum {

	MAGE("mage"),
	SORCELEUR("sorceleur"),
	GUERRIER("guerrier"),
	MARCHAND("marchand");

	@Getter
	String label;

	public ProfessionEnum getEnum(String label) {
		return EnumSet.allOf(ProfessionEnum.class)
				.stream()
				.filter(professionEnum -> professionEnum.getLabel().equals(label))
				.findFirst()
				.orElse(null);
	}

	@Override
	public String toString() {
		return this.label;
	}
}
