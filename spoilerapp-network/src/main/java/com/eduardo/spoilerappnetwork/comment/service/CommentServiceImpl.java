package com.eduardo.spoilerappnetwork.comment.service;

import com.eduardo.spoilerappnetwork.comment.dto.CommentDTO;
import com.eduardo.spoilerappnetwork.comment.dto.CommentResponseDTO;
import com.eduardo.spoilerappnetwork.comment.dto.ReplyDTO;
import com.eduardo.spoilerappnetwork.comment.entity.Comment;
import com.eduardo.spoilerappnetwork.comment.exception.CommentIsReplyException;
import com.eduardo.spoilerappnetwork.comment.exception.CommentNotFoundException;
import com.eduardo.spoilerappnetwork.comment.mapper.CommentMapper;
import com.eduardo.spoilerappnetwork.comment.repository.CommentRepository;
import com.eduardo.spoilerappnetwork.spoiler.entity.Spoiler;
import com.eduardo.spoilerappnetwork.spoiler.service.SpoilerService;
import com.eduardo.spoilerappnetwork.user.entity.User;
import com.eduardo.spoilerappnetwork.user.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService{

    private final CommentMapper commentMapper = CommentMapper.INSTANCE;
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final SpoilerService spoilerService;

    public CommentServiceImpl(CommentRepository commentRepository, UserService userService, SpoilerService spoilerService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.spoilerService = spoilerService;
    }

    @Override
    public CommentResponseDTO create(UserDetails userDetails, CommentDTO commentDTO) {
        Comment toBeSaved = this.commentMapper.toModel(commentDTO);
        createComment(userDetails, toBeSaved, commentDTO.getSpoilerId());

        Comment savedComment = this.commentRepository.save(toBeSaved);
        return commentMapper.toDTO(savedComment);
    }

    @Override
    public CommentResponseDTO createReply(UserDetails userDetails, Long parentCommentId, ReplyDTO replyDTO) {
        Comment foundParentComment = verifyAndGetIfExists(parentCommentId);
        Comment toBeSaved = this.commentMapper.toModel(replyDTO);

        createComment(userDetails, toBeSaved, replyDTO.getSpoilerId());
        toBeSaved.setParent(foundParentComment);
        Comment savedReply = this.commentRepository.save(toBeSaved);
        return commentMapper.toDTO(savedReply);
    }

    private void createComment(UserDetails userDetails, Comment comment, Long spoilerId){
        Spoiler foundSpoiler = this.spoilerService.verifyAndGetIfExists(spoilerId);
        User authUser = this.userService.verifyAndGetIfExists(userDetails.getUsername());

        comment.setAuthor(authUser);
        comment.setSpoiler(foundSpoiler);
        comment.setCreationDate(LocalDateTime.now());
    }

    @Override
    public CommentResponseDTO update(UserDetails userDetails, Long commentId, CommentDTO commentDTO) {
        User authUser = this.userService.verifyAndGetIfExists(userDetails.getUsername());
        Spoiler foundSpoiler = this.spoilerService.verifyAndGetIfExists(commentDTO.getSpoilerId());
        Comment foundComment = verifyAndGetIfExistsByIdUserAndSpoiler(commentId, authUser, foundSpoiler);

        Comment updatedComment = commentMapper.toModel(commentDTO);

        updatedComment.setId(foundComment.getId());
        updatedComment.setCreationDate(foundComment.getCreationDate());
        updatedComment.setAuthor(authUser);
        updatedComment.setSpoiler(foundSpoiler);

        Comment updated = this.commentRepository.save(updatedComment);
        return commentMapper.toDTO(updated);
    }

    @Override
    @Transactional
    public void delete(UserDetails userDetails, Long id) {
        User authUser = this.userService.verifyAndGetIfExists(userDetails.getUsername());
        Comment foundComment = verifyIfExistsByIdAndUserAndGet(id, authUser);
        this.commentRepository.deleteByIdAndAuthor(foundComment.getId(), authUser);
    }

    @Override
    public List<CommentResponseDTO> findBySpoilerId(Long spoilerId) {
        return this.commentRepository.findAllBySpoilerId(spoilerId)
                .stream()
                .map(commentMapper::toDTO)
                .collect(Collectors.toList());
    }

    private Comment verifyIfExistsByIdAndUserAndGet(Long commentId, User authUser) {
        return this.commentRepository.findByIdAndAuthor(commentId, authUser)
                .orElseThrow(() -> new CommentNotFoundException(commentId));
    }

    private Comment verifyAndGetIfExistsByIdUserAndSpoiler(Long id, User user, Spoiler spoiler){
        return this.commentRepository.findByIdAndAuthorAndSpoiler(id, user, spoiler)
                .orElseThrow(() -> new CommentNotFoundException(id));
    }

    private Comment verifyAndGetIfExists(Long id) {
        return this.commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException(id));
    }
}
