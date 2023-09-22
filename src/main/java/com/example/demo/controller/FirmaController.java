package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SolicitudesDTO;
import com.example.demo.service.FirmaService;

@CrossOrigin(origins = "https://somi.cl:8497")
@RestController
@RequestMapping("/firmas")
public class FirmaController {

    private final FirmaService firmaService;

    public FirmaController(FirmaService firmaService) {
        this.firmaService = firmaService;
    }

    @PostMapping("/primeraFirma")
    private ResponseEntity<String> primeraFirma(@RequestBody Map<String, Object> requestBody) {
        // Retrieve the ID from the request body
        String idString = (String) requestBody.get("id");
        Long id = Long.parseLong(idString);

        // Pass the ID to the service layer for further processing
        firmaService.processPrimeraFirma(id);

        // Return a success response to the client
        String message = "Solicitud firmada con éxito";
        return ResponseEntity.ok(message);
    }

    @PostMapping("/segundaFirma")
    private ResponseEntity<String> segundaFirma(@RequestBody Map<String, Object> requestBody) {
        // Retrieve the ID from the request body
        String idString = (String) requestBody.get("id");
        Long id = Long.parseLong(idString);

        // Pass the ID to the service layer for further processing
        firmaService.processSegundaFirma(id);

        // Return a success response to the client
        String message = "Solicitud firmada con éxito";
        return ResponseEntity.ok(message);
    }

    @PostMapping("/terceraFirma")
    private ResponseEntity<String> terceraFirma(@RequestBody Map<String, Object> requestBody) {
        // Retrieve the ID from the request body
        String idString = (String) requestBody.get("id");
        Long id = Long.parseLong(idString);

        // Pass the ID to the service layer for further processing
        firmaService.processTerceraFirma(id);

        // Return a success response to the client
        String message = "Solicitud firmada con éxito";
        return ResponseEntity.ok(message);
    }

    @PostMapping("/aprobado")
    private ResponseEntity<String> aprobado(@RequestBody Map<String, Object> requestBody) {
        // Retrieve the ID from the request body
        String idString = (String) requestBody.get("id");
        Long id = Long.parseLong(idString);
        // Pass the ID to the service layer for further processing
        firmaService.processAprobado(id);

        // Return a success response to the client
        String message = "Solicitud aprobada con éxito";
        return ResponseEntity.ok(message);
    }

    @PostMapping("/rechazado")
    private ResponseEntity<String> solicitudRechazar(@RequestBody Map<String, Object> requestBody) {
        // Retrieve the ID from the request body
        String idString = (String) requestBody.get("id");
        String motivoRechazo = (String) requestBody.get("motivoRechazo");
        Long id = Long.parseLong(idString);

        // Pass the ID to the service layer for further processing
        firmaService.solicitudRechazar(id, motivoRechazo);

        // Return a success response to the client
        String message = "Solicitud rechazada con éxito";
        return ResponseEntity.ok(message);
    }
}