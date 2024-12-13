package fr.meya.witcher.model.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.EnumSet;

@AllArgsConstructor
public enum RaceEnum {
	HUMAN("humain"),
	ELF("elfe"),
	ORC("orc"),
	DWARF("naim"),
	GNOME("gnome"),
	GOBLIN("gobelin");

	@Getter
	String label;

	public RaceEnum getEnum(String label) {
		return EnumSet.allOf(RaceEnum.class)
				.stream()
				.filter(raceEnum -> raceEnum.getLabel().equals(label))
				.findFirst()
				.orElse(null);
	}

	@Override
	public String toString() {
		return this.label;
	}
}
