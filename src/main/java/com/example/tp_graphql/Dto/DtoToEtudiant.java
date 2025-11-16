package com.example.tp_graphql.Dto;

import com.example.tp_graphql.entities.Centre;
import com.example.tp_graphql.entities.Etudiant;
import com.example.tp_graphql.repositories.CentreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DtoToEtudiant {
    @Autowired
    CentreRepository centreRepository;
    public void toEtudiant(Etudiant et, EtudiantDTO dto) {
        Centre centre=
                centreRepository.findById(dto.centreId()).orElse(null);
        if (dto != null) {
            et.setNom(dto.nom());
            et.setPrenom(dto.prenom());
            et.setGenre(dto.genre());
            et.setCentre(centre);
        }
    }}