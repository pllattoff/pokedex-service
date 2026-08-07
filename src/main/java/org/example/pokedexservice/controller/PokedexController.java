package org.example.pokedexservice.controller;

import jakarta.validation.Valid;
import org.example.pokedexservice.dto.request.FavoritePokemonCreateDto;
import org.example.pokedexservice.dto.request.FavoritePokemonUpdateDto;
import org.example.pokedexservice.dto.response.PokedexResponseDto;
import org.example.pokedexservice.service.PokedexService;
import org.springframework.http.HttpStatus;
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
    public PokedexResponseDto getFavoriteById(@PathVariable String id) {
        return service.getFavoriteById(id);
    }

    @PostMapping("/collection")
    @ResponseStatus(HttpStatus.CREATED)
    public PokedexResponseDto addFavorite(@RequestBody @Valid FavoritePokemonCreateDto favoritePokemonCreateDto) {
        return service.addFavorite(favoritePokemonCreateDto);
    }

    @PutMapping("/collection/{id}")
    public PokedexResponseDto updateFavorite(@PathVariable String id, @RequestBody @Valid FavoritePokemonUpdateDto favoritePokemonUpdateDto) {
        return service.updateFavorite(id, favoritePokemonUpdateDto);
    }

    @DeleteMapping("/collection/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFavorite(@PathVariable String id) {
        service.deleteFavorite(id);
    }

}
