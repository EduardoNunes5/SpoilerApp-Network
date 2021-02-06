package com.eduardo.spoilerappnetwork.comment.service;

import com.eduardo.spoilerappnetwork.comment.dto.CommentDTO;
import com.eduardo.spoilerappnetwork.comment.dto.CommentResponseDTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface CommentService {

    CommentResponseDTO create(UserDetails userDetails, CommentDTO commentDTO);
    CommentResponseDTO update(UserDetails userDetails,Long id, CommentDTO commentDTO);
    void delete(UserDetails userDetails, Long id);
}
