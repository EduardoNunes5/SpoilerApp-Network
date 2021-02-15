package com.eduardo.spoilerappnetwork.user.controller;

import com.eduardo.spoilerappnetwork.security.jwt.dto.JwtRequest;
import com.eduardo.spoilerappnetwork.security.jwt.dto.JwtResponse;
import com.eduardo.spoilerappnetwork.security.userdetails.UserDetailsServiceImpl;
import com.eduardo.spoilerappnetwork.user.dto.ResponseMessageDTO;
import com.eduardo.spoilerappnetwork.user.dto.UserDTO;
import com.eduardo.spoilerappnetwork.user.dto.UserUpdateDTO;
import com.eduardo.spoilerappnetwork.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController implements UserControllerDocs{

    private final UserService userService;
    private final UserDetailsServiceImpl userDetailsService;

    public UserController(UserService userService, UserDetailsServiceImpl userDetailsService) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseMessageDTO create(@RequestBody @Valid UserDTO userDTO) {
        return userService.create(userDTO);
    }

    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestBody JwtRequest userCredentials){
        return userDetailsService.authenticate(userCredentials);
    }

    @PatchMapping("/{id}")
    public ResponseMessageDTO update(@PathVariable Long id, @RequestBody @Valid UserUpdateDTO userDTO) {
        return userService.update(id, userDTO);
    }
}
