package com.eduardo.spoilerappnetwork.spoiler.dto;

import com.eduardo.spoilerappnetwork.user.dto.UserResponseDTO;
import com.eduardo.spoilerappnetwork.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpoilerResponseDTO {
    private Long id;

    private String name;

    private Integer episode;

    private String description;

    private UserResponseDTO author;
}
