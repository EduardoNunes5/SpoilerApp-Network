package com.eduardo.spoilerappnetwork.comment.exception;

public class CommentIsReplyException extends RuntimeException{

    public CommentIsReplyException(Long id) {
        super(String.format("Comment with id %d" +
                " is already a reply, cannot be replied once again", id));
    }
}
