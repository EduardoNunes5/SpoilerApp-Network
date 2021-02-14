package com.eduardo.spoilerappnetwork.comment.service;

import com.eduardo.spoilerappnetwork.comment.dto.CommentDTO;
import com.eduardo.spoilerappnetwork.comment.dto.CommentResponseDTO;
import com.eduardo.spoilerappnetwork.comment.dto.ReplyDTO;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface CommentService {

    CommentResponseDTO create(UserDetails userDetails, CommentDTO commentDTO);
    CommentResponseDTO createReply(UserDetails userDetails, Long parentCommentId, ReplyDTO replyDTO);
    CommentResponseDTO update(UserDetails userDetails,Long id, CommentDTO commentDTO);
    void delete(UserDetails userDetails, Long id);
    List<CommentResponseDTO> findBySpoilerId(Long spoilerId);
}
