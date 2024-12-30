package com.example.dragon_ball_api_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dragon_ball_api_backend.service.CharacterService;
import com.example.dragon_ball_api_backend.models.Character;

@RestController
@RequestMapping("/api/characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @GetMapping
    public List<Character> getAllCharacters() {
        return characterService.getAllCharacters();
    }

    @PostMapping("/fetch")
    public void fetchAndSaveCharacters(@RequestParam int totalItems) {
        characterService.fetchAndSaveCharacters(totalItems);
    }

}