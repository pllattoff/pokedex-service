package org.example.pokedexservice.dto.external.pokeapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PokeApiOtherSpritesDto(
        @JsonProperty("official-artwork")
        PokeApiOfficialArtworkDto officialArtwork
) {
}
