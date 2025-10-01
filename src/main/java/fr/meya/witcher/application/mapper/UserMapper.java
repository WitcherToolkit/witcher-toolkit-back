package fr.meya.witcher.application.mapper;

import fr.meya.witcher.domain.model.persistent.User;
import fr.meya.witcher.message.response.UserVolatile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "idUser", source = "idUser") // de l'entité vers le DTO
    UserVolatile toUserDto(User user);

    @Mapping(target = "idUser", source = "idUser") // du DTO vers l'entité
    User toUserEntity(UserVolatile dto);
}
