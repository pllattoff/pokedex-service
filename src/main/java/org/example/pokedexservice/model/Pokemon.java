package org.example.pokedexservice.model;

import lombok.Builder;

import java.util.List;

@Builder
public record Pokemon(
        String id,
        String name,
        Integer height,
        Integer weight,
        String pictureUrl,
        List<String> types
) {
}
