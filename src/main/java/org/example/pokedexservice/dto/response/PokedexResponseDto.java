package org.example.pokedexservice.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record PokedexResponseDto(
        String pokemonId,
        String pokemonName,
        String pictureUrl,
        int height,
        int weight,
        List<String> types
) {
}
