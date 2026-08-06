package org.example.pokedexservice.dto.external.pokeapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PokeApiResponseDto(
        Integer id,
        String name,
        Integer height,
        Integer weight,
        List<TypeEntryDto> types,
        SpritesDto sprites
) {
}
