package com.example.libraryapi.repository;

import com.example.libraryapi.model.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectionRepository extends JpaRepository<Collection, Long> {
    Collection findByName(String name);
}