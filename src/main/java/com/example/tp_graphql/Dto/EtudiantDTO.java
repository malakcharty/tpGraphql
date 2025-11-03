package com.example.tp_graphql.Dto;

public record EtudiantDTO(
        String nom,
        String prenom,
        String genre,
        Long centreId
) {}
