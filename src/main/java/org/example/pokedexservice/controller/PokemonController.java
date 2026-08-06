package org.example.pokedexservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PokemonController {

    @GetMapping("/pokemon/{name}")
    public String getPokemonByName(@PathVariable String name) {
        return name;
    }

}
