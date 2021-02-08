package com.eduardo.spoilerappnetwork.comment.service;

import com.eduardo.spoilerappnetwork.comment.dto.CommentDTO;
import com.eduardo.spoilerappnetwork.comment.dto.CommentResponseDTO;
import com.eduardo.spoilerappnetwork.comment.dto.ReplyDTO;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface CommentService {

    CommentResponseDTO create(UserDetails userDetails, CommentDTO commentDTO);
    CommentResponseDTO update(UserDetails userDetails,Long id, CommentDTO commentDTO);
    CommentResponseDTO createReply(UserDetails userDetails, ReplyDTO replyDTO);
    List<CommentResponseDTO> findBySpoilerId(Long spoilerId);
    void delete(UserDetails userDetails, Long id);
}
