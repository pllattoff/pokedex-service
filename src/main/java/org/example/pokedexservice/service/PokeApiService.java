package org.example.pokedexservice.service;

import org.example.pokedexservice.dto.external.pokeapi.PokeApiResponseDto;
import org.example.pokedexservice.model.Pokemon;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class PokeApiService {

    private final RestClient restClient;

    public PokeApiService(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder
                .baseUrl("https://pokeapi.co/api/v2")
                .build();
    }

    public Pokemon getPokemonByName(String name) {
        PokeApiResponseDto pokeApiResponseDto = restClient.get()
                .uri("/pokemon/" + name)
                .retrieve()
                .body(PokeApiResponseDto.class);

        return toPokemon(pokeApiResponseDto);
    }

    private Pokemon toPokemon(PokeApiResponseDto source) {
        List<String> typeNames = source.types().stream()
                .map(t -> t.type().name())
                .toList();

        String pictureUrl = source.sprites().other().officialArtwork().frontDefault();

        return Pokemon.builder()
                .id(source.id().toString())
                .name(source.name())
                .pictureUrl(pictureUrl)
                .height(source.height())
                .weight(source.weight())
                .types(typeNames)
                .build();
    }
}
