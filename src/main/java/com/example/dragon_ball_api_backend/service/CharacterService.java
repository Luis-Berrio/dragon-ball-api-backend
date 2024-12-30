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

  public void fetchAndSaveCharacters(int totalItemsToFetch) {
    String apiUrl = "https://dragonball-api.com/api/characters";
    int page = 1; 
    int itemsPerPage = 10; // Límite por página
    int fetchedItems = 0; // Contador de personajes obtenidos
    boolean hasMoreItems = true;

    while (hasMoreItems) {
        // Construir URL con paginación y límite
        String paginatedUrl = apiUrl + "?page=" + page + "&limit=" + itemsPerPage;

        // Realizar la solicitud
        ResponseEntity<ApiResponse> response = restTemplate.getForEntity(paginatedUrl, ApiResponse.class);

        if (response.getBody() != null && response.getBody().getItems() != null) {
            List<Character> characters = response.getBody().getItems();

            // Calcular cuántos personajes necesitamos de esta página
            int remainingItems = totalItemsToFetch - fetchedItems;
            int itemsToSave = Math.min(remainingItems, characters.size());

            // Guardar solo los personajes necesarios
            characterRepository.saveAll(characters.subList(0, itemsToSave));
            fetchedItems += itemsToSave;

            // Verificar si alcanzamos el total deseado
            if (fetchedItems >= totalItemsToFetch || response.getBody().getLinks().getNext() == null) {
                hasMoreItems = false; // Detener el bucle
            } else {
                page++; // Avanzar a la siguiente página
            }
        } else {
            hasMoreItems = false; // Detener si la respuesta está vacía
        }
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