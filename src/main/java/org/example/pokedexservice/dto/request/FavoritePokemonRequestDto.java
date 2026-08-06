package org.example.pokedexservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record FavoritePokemonRequestDto(
        @NotBlank
        String pokemonName,
        @NotBlank
        @Size(min = 2, max = 30)
        String nickname
) {
}
