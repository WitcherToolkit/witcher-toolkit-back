package fr.meya.witcher.application.mapper;

import fr.meya.witcher.domain.model.persistent.User;
import fr.meya.witcher.message.response.UserVolatile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", source = "iduser")
    UserVolatile toUserDto(User user);

    // Si besoin, ajoute la méthode inverse
    @Mapping(target = "iduser", source = "id")
    User toUserEntity(UserVolatile dto);
}