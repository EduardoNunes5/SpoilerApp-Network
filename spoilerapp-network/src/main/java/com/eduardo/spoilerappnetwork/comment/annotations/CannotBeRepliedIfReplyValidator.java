package com.eduardo.spoilerappnetwork.comment.annotations;

import com.eduardo.spoilerappnetwork.comment.entity.Comment;
import com.eduardo.spoilerappnetwork.comment.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class CannotBeRepliedIfReplyValidator implements ConstraintValidator<CannotBeRepliedIfReply, Long> {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public void initialize(CannotBeRepliedIfReply constraintAnnotation) {

    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {
        if(commentRepository == null) return true;
        return !commentRepository.isCommentReply(id);
    }
}
