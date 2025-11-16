package com.example.tp_graphql.service;

import com.example.tp_graphql.entities.Centre;
import com.example.tp_graphql.repositories.CentreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CentreService {

    @Autowired
    private CentreRepository centreRepository;

    public List<Centre> getCentres() {
        return centreRepository.findAll();
    }

    public Centre getCentre(Long id) {
        return centreRepository.findById(id).orElse(null);
    }
    public List<Centre> centres() {
        return getCentres();
    }

    public Centre getCentre(int id) {
        return getCentre(Long.valueOf(id));
    }
}
