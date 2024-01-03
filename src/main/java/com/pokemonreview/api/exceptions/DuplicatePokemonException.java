package com.pokemonreview.api.exceptions;

public class DuplicatePokemonException extends RuntimeException{
    private static final long serialVersionUID = 3;

    public DuplicatePokemonException(String message) {
        super(message);
    }
}
