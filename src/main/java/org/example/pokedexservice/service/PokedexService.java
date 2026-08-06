package org.example.pokedexservice.service;

import org.example.pokedexservice.dto.request.FavoritePokemonRequestDto;
import org.example.pokedexservice.dto.response.PokedexResponseDto;
import org.example.pokedexservice.model.FavoritePokemon;
import org.example.pokedexservice.model.Pokemon;
import org.example.pokedexservice.repository.FavoritePokemonRepository;
import org.springframework.stereotype.Service;

@Service
public class PokedexService {

    private final FavoritePokemonRepository repository;
    private final PokeApiService pokeApiService;
    private final IdService idService;

    public PokedexService(FavoritePokemonRepository repository, PokeApiService pokeApiService, IdService idService) {
        this.repository = repository;
        this.pokeApiService = pokeApiService;
        this.idService = idService;
    }


    public PokedexResponseDto getPokemonByName(String name) {
        Pokemon pokemon = pokeApiService.getPokemonByName(name);
        return toPokedexResponseDto(pokemon);
    }

    public PokedexResponseDto addFavorite(FavoritePokemonRequestDto favoritePokemonRequestDto) {
        Pokemon pokemon = pokeApiService.getPokemonByName(favoritePokemonRequestDto.pokemonName());

        FavoritePokemon favoritePokemon = FavoritePokemon.builder()
                .id(idService.randomId())
                .pokemonId(pokemon.id())
                .nickname(favoritePokemonRequestDto.nickname())
                .pokemonName(pokemon.name())
                .pictureUrl(pokemon.pictureUrl())
                .height(pokemon.height())
                .weight(pokemon.weight())
                .types(pokemon.types())
                .build();
        FavoritePokemon saved = repository.save(favoritePokemon);

        return toPokedexResponseDto(saved);
    }

    private PokedexResponseDto toPokedexResponseDto(Pokemon source) {
        return PokedexResponseDto.builder()
                .pokemonId(source.id())
                .pokemonName(source.name())
                .pictureUrl(source.pictureUrl())
                .height(source.height())
                .weight(source.weight())
                .types(source.types())
                .build();
    }

    private PokedexResponseDto toPokedexResponseDto(FavoritePokemon source) {
        return PokedexResponseDto.builder()
                .id(source.id())
                .pokemonId(source.pokemonId())
                .nickname(source.nickname())
                .pokemonName(source.pokemonName())
                .pictureUrl(source.pictureUrl())
                .height(source.height())
                .weight(source.weight())
                .types(source.types())
                .build();
    }
}
