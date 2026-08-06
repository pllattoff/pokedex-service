package org.example.pokedexservice.dto.external.pokeapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PokeApiTypeSlotDto(
        Integer slot,
        PokeApiTypeDto type
) {
}
