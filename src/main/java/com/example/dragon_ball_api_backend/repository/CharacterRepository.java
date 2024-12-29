package com.example.dragon_ball_api_backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.dragon_ball_api_backend.models.Character;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends MongoRepository<Character, String> {
}