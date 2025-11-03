package com.example.tp_graphql.web;

import com.example.tp_graphql.Dto.EtudiantDTO;
import com.example.tp_graphql.entities.Centre;
import com.example.tp_graphql.entities.Etudiant;
import com.example.tp_graphql.entities.Genre;
import com.example.tp_graphql.repositories.CentreRepository;
import com.example.tp_graphql.repositories.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class EtudiantGraphQLController {
    @Autowired
    private EtudiantRepository etudiantRepository;
    @Autowired
    private CentreRepository centreRepository;

    @QueryMapping
    public List<Etudiant> listEtudiants(){
        return etudiantRepository.findAll();
    }

    @QueryMapping
    public Etudiant getEtudiantById(@Argument Long id){
        return etudiantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("etudiant %d non trouvé ", id)));
    }

    @QueryMapping
    public List<Centre> centres(){
        return centreRepository.findAll();
    }

    @QueryMapping
    public Centre getCentreById(@Argument Long id){
        return centreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Centre %s non trouvé ", id)));
    }

    @MutationMapping
    public Etudiant addEtudiant(@Argument EtudiantDTO etudiant){
        Centre centre = centreRepository.findById(etudiant.centreId()).orElse(null);

        Etudiant et = new Etudiant();
        et.setNom(etudiant.nom());
        et.setPrenom(etudiant.prenom());
        // ⬇️ Convertit le String du DTO vers l'enum Genre attendu par l'entité
        if (etudiant.genre() != null) {
            et.setGenre(Genre.valueOf(etudiant.genre())); // la valeur doit correspondre EXACTEMENT à l’enum
        }
        et.setCentre(centre);

        return etudiantRepository.save(et);
    }

    @MutationMapping
    public Etudiant updateEtudiant(@Argument Long id, @Argument EtudiantDTO etudiant){
        Centre centre = centreRepository.findById(etudiant.centreId()).orElse(null);

        return etudiantRepository.findById(id).map(et -> {
            if (etudiant.nom() != null)    et.setNom(etudiant.nom());
            if (etudiant.prenom() != null) et.setPrenom(etudiant.prenom());
            if (etudiant.genre() != null)  et.setGenre(Genre.valueOf(etudiant.genre())); // ⬅️ pas Etudiant.genre()
            if (centre != null)            et.setCentre(centre);
            return etudiantRepository.save(et);
        }).orElse(null);
    }

    @MutationMapping
    public String deleteEtudiant(@Argument Long id){
        if (etudiantRepository.findById(id).isPresent()){
            etudiantRepository.deleteById(id);
            return String.format("L'étudiant %s bien supprimé ", id);
        } else {
            return String.format("L'étudiant %s n'existe pas ", id);
        }
    }
}
