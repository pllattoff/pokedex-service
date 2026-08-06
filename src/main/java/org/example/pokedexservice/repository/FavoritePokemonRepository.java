package org.example.pokedexservice.repository;

import org.example.pokedexservice.model.FavoritePokemon;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoritePokemonRepository extends MongoRepository<FavoritePokemon, String> {
}
