package fr.meya.witcher.message.response;

import fr.meya.witcher.domain.model.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVolatile {

    private Long id;

    private String email;

    private String password;

    private Set<RoleEnum> roles;
}
