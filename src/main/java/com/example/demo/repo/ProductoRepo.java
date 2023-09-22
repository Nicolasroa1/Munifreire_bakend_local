package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Producto;

import java.util.*;

@Repository
public interface ProductoRepo extends JpaRepository<Producto, Long> {

        @Query("SELECT t FROM Producto t WHERE t.descripcion LIKE %?1%")
        List<Producto> findProductoByDescripcion(String descripcion);

        Optional<Producto> findProductoById(Long id);

}