package com.eduardo.spoilerappnetwork.user.mapper;

import com.eduardo.spoilerappnetwork.user.dto.UserDTO;
import com.eduardo.spoilerappnetwork.user.dto.UserResponseDTO;
import com.eduardo.spoilerappnetwork.user.dto.UserUpdateDTO;
import com.eduardo.spoilerappnetwork.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toModel(UserDTO userDTO);

    User toModel(UserUpdateDTO userUpdateDTO);

    UserResponseDTO toDTO(User model);
}
