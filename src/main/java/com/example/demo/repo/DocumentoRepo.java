package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Documento;

@Repository
public interface DocumentoRepo extends JpaRepository<Documento, Long> {
    Documento save(Documento documento);
}