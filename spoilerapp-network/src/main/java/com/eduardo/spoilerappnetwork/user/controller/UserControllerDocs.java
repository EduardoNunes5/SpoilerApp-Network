package com.eduardo.spoilerappnetwork.user.controller;

import com.eduardo.spoilerappnetwork.security.jwt.dto.JwtRequest;
import com.eduardo.spoilerappnetwork.security.jwt.dto.JwtResponse;
import com.eduardo.spoilerappnetwork.user.dto.ResponseMessageDTO;
import com.eduardo.spoilerappnetwork.user.dto.UserDTO;
import com.eduardo.spoilerappnetwork.user.dto.UserUpdateDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Api("Users operations")
public interface UserControllerDocs {

    @ApiOperation("user creation operation")
    @ApiResponses( value = {
            @ApiResponse(code = 201, message = "user created"),
            @ApiResponse(code = 400, message = "user already exists/missing fields")
    })
    ResponseMessageDTO create(UserDTO userDTO);

    @ApiOperation("User authentication operation")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "user authenticated"),
            @ApiResponse(code = 404, message = "user not found")
    })
    JwtResponse authenticate(JwtRequest userCredentials);

    @ApiOperation("User password/email updation operation")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "user updated"),
            @ApiResponse(code = 404, message = "user not found")
    })
    ResponseMessageDTO update(Long id, UserUpdateDTO userDTO);
}
