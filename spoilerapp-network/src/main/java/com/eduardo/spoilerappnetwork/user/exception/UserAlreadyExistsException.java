package com.eduardo.spoilerappnetwork.user.exception;

import javax.persistence.EntityExistsException;

public class UserAlreadyExistsException extends EntityExistsException {

    public UserAlreadyExistsException(String email) {
        super(String.format("User with email %s already exists", email));
    }
}
