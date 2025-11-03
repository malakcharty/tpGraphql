package com.example.tp_graphql.repositories;

import com.example.tp_graphql.entities.Centre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CentreRepository extends JpaRepository<Centre,Long> {
}