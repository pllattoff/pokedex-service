package org.example.pokedexservice.service;

import org.example.pokedexservice.dto.external.pokeapi.PokeApiResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class PokeApiService {

    private final RestClient restClient;

    public PokeApiService(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder
                .baseUrl("https://pokeapi.co/api/v2")
                .build();
    }

    public PokeApiResponseDto getPokemonByName(String name) {
        return restClient.get()
                .uri("/pokemon/" + name)
                .retrieve()
                .body(PokeApiResponseDto.class);
    }
}
