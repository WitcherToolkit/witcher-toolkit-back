package fr.meya.witcher.message.response;

import fr.meya.witcher.domain.model.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVolatile {

    private UUID idUser;

    private String email;

    private String pseudo;

    private String password;

    private Set<RoleEnum> roles;
}
