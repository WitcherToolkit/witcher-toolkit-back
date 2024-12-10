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
public class Sort extends Magie{

	private String cout;

	private String niveau;

	@ManyToMany
	@JoinTable(
			name = "personnageSort",
			joinColumns = @JoinColumn(name = "idSort"),
			inverseJoinColumns = @JoinColumn(name = "idPersonnage"))
	private List<Personnage> personnageList;

}
