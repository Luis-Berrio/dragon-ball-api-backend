package com.example.dragon_ball_api_backend.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "characters")
public class Character {
    @Id
    private String id;
    private String name;
    private String ki;
    private String maxKi;
    private String race;
    private String gender;
    private String description;
    private String image;
    private String affiliation;
    private Planet originPlanet;
    private List<Transformation> transformations;
}

@Data
class Planet {
    private String id;
    private String name;
    private boolean isDestroyed;
    private String description;
    private String image;
}

@Data
class Transformation {
    private String name;
    private String ki;
    private String image;
}