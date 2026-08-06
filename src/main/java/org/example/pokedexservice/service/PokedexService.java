package org.example.pokedexservice.service;

import org.example.pokedexservice.dto.external.pokeapi.PokeApiResponseDto;
import org.example.pokedexservice.dto.response.PokedexResponseDto;
import org.example.pokedexservice.repository.FavoritePokemonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokedexService {

    private final FavoritePokemonRepository repository;
    private final PokeApiService pokeApiService;

    public PokedexService(FavoritePokemonRepository repository, PokeApiService pokeApiService) {
        this.repository = repository;
        this.pokeApiService = pokeApiService;
    }


    public PokedexResponseDto getPokemonByName(String name) {
        PokeApiResponseDto pokeApiResponseDto = pokeApiService.getPokemonByName(name);
        return toPokedexResponseDto(pokeApiResponseDto);
    }

    private PokedexResponseDto toPokedexResponseDto(PokeApiResponseDto source) {
        List<String> typeNames = source.types().stream()
                .map(t -> t.type().name())
                .toList();

        String pictureUrl = source.sprites().other().officialArtwork().frontDefault();

        return PokedexResponseDto.builder()
                .pokemonId(source.id().toString())
                .pokemonName(source.name())
                .pictureUrl(pictureUrl)
                .height(source.height())
                .weight(source.weight())
                .types(typeNames)
                .build();
    }
}
