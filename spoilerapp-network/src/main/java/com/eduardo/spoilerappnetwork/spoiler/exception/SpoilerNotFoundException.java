package com.eduardo.spoilerappnetwork.spoiler.exception;

import javax.persistence.EntityNotFoundException;

public class SpoilerNotFoundException extends EntityNotFoundException {
    public SpoilerNotFoundException(Long id) {
        super(String.format("Spoiler with id %d not found", id));
    }
}
