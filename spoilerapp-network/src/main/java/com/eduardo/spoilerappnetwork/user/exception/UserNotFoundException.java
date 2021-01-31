package com.eduardo.spoilerappnetwork.user.exception;

import javax.persistence.EntityNotFoundException;

public class UserNotFoundException extends EntityNotFoundException {
    public UserNotFoundException(Long id) {
        super(String.format("User with id %d not found", id));
    }
}
