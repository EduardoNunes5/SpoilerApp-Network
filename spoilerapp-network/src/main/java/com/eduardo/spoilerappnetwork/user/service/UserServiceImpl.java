package com.eduardo.spoilerappnetwork.user.service;

import com.eduardo.spoilerappnetwork.user.dto.ResponseMessageDTO;
import com.eduardo.spoilerappnetwork.user.dto.UserDTO;
import com.eduardo.spoilerappnetwork.user.dto.UserUpdateDTO;
import com.eduardo.spoilerappnetwork.user.entity.User;
import com.eduardo.spoilerappnetwork.user.exception.UserAlreadyExistsException;
import com.eduardo.spoilerappnetwork.user.exception.UserNotFoundException;
import com.eduardo.spoilerappnetwork.user.mapper.UserMapper;
import com.eduardo.spoilerappnetwork.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.eduardo.spoilerappnetwork.user.utils.ResposeMessageHandler.creationMessage;
import static com.eduardo.spoilerappnetwork.user.utils.ResposeMessageHandler.updationMessage;

@Service
public class UserServiceImpl implements  UserService{

    private final UserRepository userRepository;
    private  final UserMapper userMapper = UserMapper.INSTANCE;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseMessageDTO create(UserDTO userDTO) {
        verifyIfUserExists(userDTO.getUsername(), userDTO.getEmail());

        User toBeSaved = userMapper.toModel(userDTO);
        toBeSaved.setPassword(passwordEncoder.encode(toBeSaved.getPassword()));
        User saved = this.userRepository.save(toBeSaved);

        return creationMessage(saved.getId());
    }

    @Override
    public ResponseMessageDTO update(Long id, UserUpdateDTO userDTO) {
        User user = verifyAndGetIfExists(id);

        User updatedModel = userMapper.toModel(userDTO);
        updatedModel.setId(id);
        updatedModel.setUsername(user.getUsername());

        User updated = this.userRepository.save(updatedModel);
        return updationMessage(updated.getId());
    }

    private void verifyIfUserExists(String username, String email) {
        this.userRepository.findByUsernameOrEmail(username, email)
                .ifPresent( u -> {
                    throw new UserAlreadyExistsException(username);
                });
    }

    private User verifyAndGetIfExists(Long id){
        return this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

    }

    public User verifyAndGetIfExists(String username){
        return this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
    }

    @Override
    public List<UserDTO> findAll() {
        return null;
    }
}
