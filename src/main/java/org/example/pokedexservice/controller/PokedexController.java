package org.example.pokedexservice.controller;

import jakarta.validation.Valid;
import org.example.pokedexservice.dto.request.FavoritePokemonRequestDto;
import org.example.pokedexservice.dto.response.PokedexResponseDto;
import org.example.pokedexservice.service.PokedexService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PokedexController {

    private final PokedexService service;

    public PokedexController(PokedexService service) {
        this.service = service;
    }

    @GetMapping("/pokemon/{name}")
    public PokedexResponseDto getPokemonByName(@PathVariable String name) {
        return service.getPokemonByName(name);
    }

    @GetMapping("/collection")
    public List<PokedexResponseDto> getFavorites() {
        return service.getFavorites();
    }

    @GetMapping("/collection/{id}")
    public PokedexResponseDto getFavoritesById(@PathVariable String id) {
        return service.getFavoriteById(id);
    }

    @PostMapping("/collection")
    public PokedexResponseDto addFavorite(@RequestBody @Valid FavoritePokemonRequestDto favoritePokemonRequestDto) {
        return service.addFavorite(favoritePokemonRequestDto);
    }

}
