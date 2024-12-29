package com.example.dragon_ball_api_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.dragon_ball_api_backend.models.Character;
import com.example.dragon_ball_api_backend.repository.CharacterRepository;

import lombok.Data;

@Service
public class CharacterService {

  @Autowired
  private CharacterRepository characterRepository;

  @Autowired
  private RestTemplate restTemplate;

  public void fetchAndSaveCharacters() {
    String apiUrl = "https://dragonball-api.com/api/characters";
    ResponseEntity<ApiResponse> response = restTemplate.getForEntity(apiUrl, ApiResponse.class);

    if (response.getBody() != null && response.getBody().getItems() != null) {
      List<Character> characters = response.getBody().getItems();
      characterRepository.saveAll(characters);
    }
  }

  public List<Character> getAllCharacters() {
    return characterRepository.findAll();
  }

}

@Data
class ApiResponse {
  private List<Character> items;
  private Meta meta;
  private Links links;
}

@Data
class Meta {
  private int totalItems;
  private int itemCount;
  private int itemsPerPage;
  private int totalPages;
  private int currentPage;
}

@Data
class Links {
  private String first;
  private String previous;
  private String next;
  private String last;
}