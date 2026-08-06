package org.example.pokedexservice.service;

import org.example.pokedexservice.repository.PokemonRepository;
import org.springframework.stereotype.Service;

@Service
public class PokemonService {

    private final PokemonRepository repository;

    public PokemonService(PokemonRepository repository) {
        this.repository = repository;
    }
}
