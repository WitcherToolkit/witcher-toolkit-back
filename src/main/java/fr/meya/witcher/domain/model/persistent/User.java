package fr.meya.witcher.domain.model.persistent;

import fr.meya.witcher.domain.model.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Users")
public class User {

    @PrePersist
    public void generateId() {
        if (this.idUser == null) {
            this.idUser = UUID.randomUUID();
        }
    }

	@Id
	@Column(name = "id_user")
	private UUID idUser;

	@Column(unique = true, nullable = false)
	private String email;

	private String pseudo;

	@Column(nullable = false)
	private String password;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "id_user"))// Définit la table utilisée pour stocker la collection (user_roles).
	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private Set<RoleEnum> roles = new HashSet<>();// Initialisation par défaut avec new HashSet<>() pour éviter les NullPointerException.

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true) // Propage les opérations (persist, delete) sur les personnages lorsqu’un user est supprimé.
	private List<Personnage> personnageList = new ArrayList<>();

	@OneToMany(mappedBy = "user")
	private List<Campagne> campagneList = new ArrayList<>();

}
