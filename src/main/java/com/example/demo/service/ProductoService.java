package com.example.demo.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repo.ProductoRepo;
import com.example.demo.exception.UserNotFoundException;

import com.example.demo.model.Producto;

@Service
public class ProductoService {

    @Autowired
    private final ProductoRepo productoRepo;

    public ProductoService(ProductoRepo productoRepo) {
        this.productoRepo = productoRepo;

    }

    public List<Producto> findAllProductos(String descripcion) {
        return productoRepo.findProductoByDescripcion(descripcion);
    }

    public Producto findProductoById(Long id) {
        return productoRepo.findProductoById(id)
                .orElseThrow(() -> new UserNotFoundException("user by id" + id + "not found"));
    }

    public Producto getProductoById(Long id) {
        return productoRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Producto with id " + id + " not found"));
    }

}
