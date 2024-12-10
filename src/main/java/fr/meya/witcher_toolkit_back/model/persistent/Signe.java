package fr.meya.witcher_toolkit_back.model.persistent;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Signe extends Magie{

	@ManyToMany
	@JoinTable(
			name = "personnageMagie",
			joinColumns = @JoinColumn(name = "idMagie"),
			inverseJoinColumns = @JoinColumn(name = "idPersonnage"))
	private List<Personnage> personnageList;

}
