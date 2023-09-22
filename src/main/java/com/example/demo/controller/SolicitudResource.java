package com.example.demo.controller;

import java.util.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;

import com.example.demo.service.SolicitudService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.demo.dto.ProductosSeleccionadosDTO;
import com.example.demo.dto.SolicitudUsuarioXDTO;
import com.example.demo.dto.SolicitudesDTO;
import com.example.demo.model.OrdenDeCompraSolicitud;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8000")
@RestController
@RequestMapping("/solicitudes")
public class SolicitudResource {
    private final SolicitudService solicitudService;

    public SolicitudResource(SolicitudService solicitudService) {
        this.solicitudService = solicitudService;
    }

    @PostMapping(value = "/addOrden", consumes = { "multipart/form-data" })
    public ResponseEntity<OrdenDeCompraSolicitud> processOrdenDeCompra(
            @RequestParam(name = "file", required = false) MultipartFile[] file,
            @RequestPart("productosSeleccionados") String productosSeleccionadosJSON,
            @RequestParam("currentUserId") Long currentUserId,
            @RequestParam("departamento") String departamento,
            @RequestParam("destino") String destino) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<ProductosSeleccionadosDTO> productosSeleccionados = mapper.readValue(productosSeleccionadosJSON,
                new TypeReference<List<ProductosSeleccionadosDTO>>() {
                });

        OrdenDeCompraSolicitud savedOrdenDeCompra = solicitudService.processOrdenDeCompra(productosSeleccionados,
                currentUserId, departamento, destino);
        if (file != null) {
            for (MultipartFile f : file) {
                if (f != null && !f.isEmpty()) {
                    solicitudService.uploadFile(f);
                }
            }
        }
        return new ResponseEntity<>(savedOrdenDeCompra, HttpStatus.OK);
    }

    @GetMapping("/byEstado/{estado}")
    public ResponseEntity<List<SolicitudesDTO>> getSolicitudes(@PathVariable("estado") String estado,
            @RequestParam("departamento") String departamento) {

        List<Object[]> solicitudes = solicitudService.getAllSolicitudes(estado, departamento);

        List<SolicitudesDTO> solicitudesList = new ArrayList<>();
        for (Object[] result : solicitudes) {
            SolicitudesDTO dto = new SolicitudesDTO();
            dto.setId(((Number) result[0]).longValue());
            Timestamp timestamp = (Timestamp) result[1];

            // Create a SimpleDateFormat object with the desired format
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            // Format the timestamp to a string in the desired format
            String formattedDate = dateFormat.format(timestamp);

            // Set the formatted date in your DTO object
            dto.setFecha_solicitud(formattedDate);

            dto.setNombre(((String) result[2]));
            dto.setEstado(((String) result[3]));
            solicitudesList.add(dto);
        }
        return new ResponseEntity<>(solicitudesList, HttpStatus.OK);
    }

    @GetMapping("/byUserId/{id}")
    public ResponseEntity<List<SolicitudesDTO>> getSolicitudesByUserId(@PathVariable("id") Long id) {

        List<Object[]> solicitudes = solicitudService.getAllSolicitudesByUserId(id);

        List<SolicitudesDTO> solicitudesList = new ArrayList<>();
        for (Object[] result : solicitudes) {
            SolicitudesDTO dto = new SolicitudesDTO();
            dto.setId(((Number) result[0]).longValue());
            Timestamp timestamp = (Timestamp) result[1];

            // Create a SimpleDateFormat object with the desired format
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            // Format the timestamp to a string in the desired format
            String formattedDate = dateFormat.format(timestamp);

            // Set the formatted date in your DTO object
            dto.setFecha_solicitud(formattedDate);
            dto.setNombre(((String) result[2]));
            dto.setEstado(((String) result[3]));
            solicitudesList.add(dto);
        }
        return new ResponseEntity<>(solicitudesList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<SolicitudUsuarioXDTO>> getOrdenDeCompraById(@PathVariable("id") Long id) {
        List<SolicitudUsuarioXDTO> solicitudUsuarioXDTO = solicitudService.findOrdenDeCompraById(id);
        return new ResponseEntity<List<SolicitudUsuarioXDTO>>(solicitudUsuarioXDTO, HttpStatus.OK);
    }
}