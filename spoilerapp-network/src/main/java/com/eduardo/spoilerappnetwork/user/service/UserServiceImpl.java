package com.eduardo.spoilerappnetwork.user.service;

import com.eduardo.spoilerappnetwork.user.dto.ResponseMessageDTO;
import com.eduardo.spoilerappnetwork.user.dto.UserDTO;
import com.eduardo.spoilerappnetwork.user.entity.User;
import com.eduardo.spoilerappnetwork.user.exception.UserAlreadyExistsException;
import com.eduardo.spoilerappnetwork.user.exception.UserNotFoundException;
import com.eduardo.spoilerappnetwork.user.mapper.UserMapper;
import com.eduardo.spoilerappnetwork.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.eduardo.spoilerappnetwork.user.utils.ResposeMessageHandler.creationMessage;
import static com.eduardo.spoilerappnetwork.user.utils.ResposeMessageHandler.updationMessage;

@Service
public class UserServiceImpl implements  UserService{

    private final UserRepository userRepository;
    private  final UserMapper userMapper = UserMapper.INSTANCE;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseMessageDTO create(UserDTO userDTO) {
        verifyIfUserExists(userDTO.getEmail());

        User toBeSaved = userMapper.toModel(userDTO);
        User saved = this.userRepository.save(toBeSaved);

        return creationMessage(saved.getId());
    }

    @Override
    public ResponseMessageDTO update(Long id, UserDTO userDTO) {
        verifyAndGetIfExists(id);

        User updatedModel = userMapper.toModel(userDTO);
        updatedModel.setId(id);

        User updated = this.userRepository.save(updatedModel);
        return updationMessage(updated.getId());
    }

    private void verifyIfUserExists(String email) {
        this.userRepository.findByEmail(email)
                .ifPresent( u -> {
                    throw new UserAlreadyExistsException(email);
                });
    }

    private User verifyAndGetIfExists(Long id){
        return this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

    }

    @Override
    public List<UserDTO> findAll() {
        return null;
    }
}
