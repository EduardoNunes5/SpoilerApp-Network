package com.eduardo.spoilerappnetwork.comment.mapper;

import com.eduardo.spoilerappnetwork.comment.dto.CommentDTO;
import com.eduardo.spoilerappnetwork.comment.dto.CommentResponseDTO;
import com.eduardo.spoilerappnetwork.comment.dto.ReplyDTO;
import com.eduardo.spoilerappnetwork.comment.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    CommentResponseDTO toDTO(Comment comment);
    Comment toModel(CommentDTO commentDTO);
    Comment toModel(ReplyDTO replyDTO);

}
