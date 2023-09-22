package com.example.demo.controller;

import java.util.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.service.ProductoService;

import com.example.demo.model.Producto;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8000")
@RestController
@RequestMapping("/productos")
public class ProductoResource {
    private final ProductoService productoService;

    public ProductoResource(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/{descripcion}")
    public ResponseEntity<List<Producto>> getAllProducto(@PathVariable("descripcion") String descripcion) {

        List<Producto> productos = productoService.findAllProductos(descripcion);
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable("id") Long id) {
        Producto producto = productoService.findProductoById(id);
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

}