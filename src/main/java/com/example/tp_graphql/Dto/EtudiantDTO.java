package com.example.tp_graphql.Dto;

import com.example.tp_graphql.entities.Genre;

public record EtudiantDTO(
        String nom,
        String prenom,
        Genre genre,
        Long centreId
) {}
