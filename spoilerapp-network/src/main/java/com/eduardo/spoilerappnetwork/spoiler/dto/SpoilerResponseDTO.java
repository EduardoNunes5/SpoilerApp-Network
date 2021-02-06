package com.eduardo.spoilerappnetwork.spoiler.dto;

import com.eduardo.spoilerappnetwork.comment.dto.CommentResponseDTO;
import com.eduardo.spoilerappnetwork.user.dto.UserResponseDTO;
import com.eduardo.spoilerappnetwork.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
