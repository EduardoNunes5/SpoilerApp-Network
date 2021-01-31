package com.eduardo.spoilerappnetwork.user.service;

import com.eduardo.spoilerappnetwork.user.dto.ResponseMessageDTO;
import com.eduardo.spoilerappnetwork.user.dto.UserDTO;
import com.eduardo.spoilerappnetwork.user.entity.User;
import com.eduardo.spoilerappnetwork.user.exception.UserAlreadyExistsException;
import com.eduardo.spoilerappnetwork.user.mapper.UserMapper;
import com.eduardo.spoilerappnetwork.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.eduardo.spoilerappnetwork.user.utils.ResposeMessageHandler.creationMessage;

@Service
public class UserServiceImpl implements  UserService{

    private final UserRepository userRepository;
    private  final UserMapper userMapper = UserMapper.INSTANCE;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseMessageDTO create(UserDTO userDTO) {
        System.out.println("dto:  " + userDTO);
        verifyIfUserExists(userDTO.getEmail());

        User toBeSaved = userMapper.toModel(userDTO);
        System.out.println("model::  " + toBeSaved);

        User saved = this.userRepository.save(toBeSaved);
        return creationMessage(saved.getId());
    }

    private void verifyIfUserExists(String email) {
        this.userRepository.findByEmail(email)
                .ifPresent( u -> {
                    throw new UserAlreadyExistsException(email);
                });
    }

    @Override
    public ResponseMessageDTO update(UserDTO userDTO) {
        return null;
    }

    @Override
    public List<UserDTO> findAll() {
        return null;
    }
}
