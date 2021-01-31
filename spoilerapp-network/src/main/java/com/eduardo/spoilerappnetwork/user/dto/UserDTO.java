package com.eduardo.spoilerappnetwork.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;


    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    private String email;

    @NotNull
    @Size(min = 8)
    private String password;
}
