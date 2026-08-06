package org.example.pokedexservice.controller;

import org.example.pokedexservice.dto.response.PokedexResponseDto;
import org.example.pokedexservice.service.PokedexService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
