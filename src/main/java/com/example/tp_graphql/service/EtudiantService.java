package com.example.tp_graphql.service;

import com.example.tp_graphql.Dto.DtoToEtudiant;
import com.example.tp_graphql.Dto.EtudiantDTO;
import com.example.tp_graphql.entities.Etudiant;
import com.example.tp_graphql.repositories.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.List;

@Service
public class EtudiantService {
    @Autowired
    DtoToEtudiant dtoToEtudiant;
    @Autowired
    EtudiantRepository etudiantRepository;
    private final Sinks.Many<Etudiant> sink = Sinks.many().multicast().onBackpressureBuffer();

    public List<Etudiant> getStudents() {
        return etudiantRepository.findAll();
    }

    public Etudiant getEtudiant(Long id) {
        return etudiantRepository.findById(id).orElse(null);
    }

    public Etudiant addEtudiant(EtudiantDTO etudiantDTO) {
        Etudiant etudiant = new Etudiant();
        dtoToEtudiant.toEtudiant(etudiant, etudiantDTO);
        etudiantRepository.save(etudiant);
        sink.tryEmitNext(etudiant);
        return etudiant;
    }
    public Etudiant updateEtudiant(Long id, EtudiantDTO etudiantDTO){
        if(etudiantRepository.findById(id).isPresent()){
            Etudiant etudiant=etudiantRepository.findById(id).get();
            dtoToEtudiant.toEtudiant(etudiant,etudiantDTO);
            return etudiantRepository.save(etudiant);
        }
        return null;
    }
    public Flux<Etudiant> getEtudiantAddedPublisher() {
        return sink.asFlux();
    }
    public String deleteEtudiant(Long id) {
        if (etudiantRepository.findById(id).isPresent()) {
            etudiantRepository.deleteById(id);
            return String.format("l'étudiant %s est bien supprimé !", id);
        }
        return String.format("l'étudiant %s n'existe pas!", id);
    }
}