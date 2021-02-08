package com.eduardo.spoilerappnetwork.comment.dto;

import com.eduardo.spoilerappnetwork.user.dto.UserResponseDTO;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class CommentResponseDTO {

    private Long id;

    private String commentText;

    private UserResponseDTO author;

    private LocalDateTime creationDate;

}
