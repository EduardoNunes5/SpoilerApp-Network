package com.eduardo.spoilerappnetwork.comment.exception;

import javax.persistence.EntityNotFoundException;

public class CommentNotFoundException extends EntityNotFoundException {
    public CommentNotFoundException(Long id) {
        super(String.format("Comment with id %d not found", id));
    }
}
