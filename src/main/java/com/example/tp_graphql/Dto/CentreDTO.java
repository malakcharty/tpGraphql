package com.example.tp_graphql.Dto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CentreDTO {
    private Long id;
    private String nom;
    private String adresse;
}