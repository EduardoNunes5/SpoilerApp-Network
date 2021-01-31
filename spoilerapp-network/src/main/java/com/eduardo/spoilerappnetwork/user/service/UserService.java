package com.eduardo.spoilerappnetwork.user.service;

import com.eduardo.spoilerappnetwork.user.dto.ResponseMessageDTO;
import com.eduardo.spoilerappnetwork.user.dto.UserDTO;
import com.eduardo.spoilerappnetwork.user.entity.User;

import java.util.List;

public interface UserService {

    ResponseMessageDTO create(UserDTO userDTO);
    ResponseMessageDTO update(Long id, UserDTO userDTO);
    List<UserDTO> findAll();
    User verifyAndGetIfExists(Long id);

}
