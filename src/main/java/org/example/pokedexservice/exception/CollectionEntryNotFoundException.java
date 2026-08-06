package org.example.pokedexservice.exception;

public class CollectionEntryNotFoundException extends RuntimeException {
    public CollectionEntryNotFoundException(String message) {
        super(message);
    }
}
