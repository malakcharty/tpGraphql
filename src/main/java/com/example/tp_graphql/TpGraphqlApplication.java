package com.example.tp_graphql;

import com.example.tp_graphql.entities.Centre;
import com.example.tp_graphql.entities.Etudiant;
import com.example.tp_graphql.entities.Genre;
import com.example.tp_graphql.repositories.CentreRepository;
import com.example.tp_graphql.repositories.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TpGraphqlApplication implements CommandLineRunner {

    private final EtudiantRepository etudiantRepository;
    private final CentreRepository centreRepository;

    @Autowired
    public TpGraphqlApplication(EtudiantRepository etudiantRepository,
                                CentreRepository centreRepository) {
        this.etudiantRepository = etudiantRepository;
        this.centreRepository = centreRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(TpGraphqlApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Centre centre1 = Centre.builder()
                .nom("Maarif")
                .adresse("Biranzarane")
                .build();
        centreRepository.save(centre1);

        Centre centre2 = Centre.builder()
                .nom("Oranges")
                .adresse("Oulfa")
                .build();
        centreRepository.save(centre2);

        // --- Étudiants de test ---
        Etudiant et1 = Etudiant.builder()
                .nom("Adnani")
                .prenom("Brahim")
                .genre(Genre.Homme)
                .centre(centre1)
                .build();
        etudiantRepository.save(et1);

        Etudiant et2 = Etudiant.builder()
                .nom("Sara")
                .prenom("El Idrissi")
                .genre(Genre.Femme)
                .centre(centre2)
                .build();
        etudiantRepository.save(et2);
    }
}
