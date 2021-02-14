package com.eduardo.spoilerappnetwork.comment.controller;

import com.eduardo.spoilerappnetwork.comment.dto.CommentDTO;
import com.eduardo.spoilerappnetwork.comment.dto.CommentResponseDTO;
import com.eduardo.spoilerappnetwork.comment.dto.ReplyDTO;
import com.eduardo.spoilerappnetwork.comment.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponseDTO create(@AuthenticationPrincipal UserDetails userDetails, @RequestBody @Valid CommentDTO commentDTO) {
        return commentService.create(userDetails, commentDTO);
    }

    @PostMapping("/{parentCommentId}/replies")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponseDTO createReply(@AuthenticationPrincipal UserDetails userDetails,@PathVariable Long parentCommentId, @RequestBody @Valid ReplyDTO replyDTO) {
        return commentService.createReply(userDetails, parentCommentId, replyDTO);
    }

    @PutMapping("/{commentId}")
    public CommentResponseDTO update(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long commentId, @RequestBody @Valid CommentDTO commentDTO) {
        return commentService.update(userDetails, commentId, commentDTO);
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long commentId) {
        commentService.delete(userDetails, commentId);
    }

    @DeleteMapping("/{commentId}/replies")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReply(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long commentId){
        commentService.delete(userDetails, commentId);
    }

    @GetMapping("/spoilers/{spoilerId}")
    public List<CommentResponseDTO> findBySpoilerId(@PathVariable Long spoilerId) {
        return commentService.findBySpoilerId(spoilerId);
    }
}
