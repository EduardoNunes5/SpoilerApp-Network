package com.eduardo.spoilerappnetwork.user.service;

import com.eduardo.spoilerappnetwork.user.dto.ResponseMessageDTO;
import com.eduardo.spoilerappnetwork.user.dto.UserDTO;

import java.util.List;

public interface UserService {

    ResponseMessageDTO create(UserDTO userDTO);
    ResponseMessageDTO update(UserDTO userDTO);
    List<UserDTO> findAll();

}
