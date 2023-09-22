package com.example.demo.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repo.FirmaRepo;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.OrdenDeCompra;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FirmaService {

    @Autowired
    private final FirmaRepo firmaRepo;

    public FirmaService(FirmaRepo firmaRepo) {
        this.firmaRepo = firmaRepo;

    }

    public void processPrimeraFirma(Long solicitudId) {
        firmaRepo.updateEstadoToSignedIfPending(solicitudId);

        Optional<OrdenDeCompra> firmaOptional = firmaRepo.findById(solicitudId);

        if (firmaOptional.isPresent()) {
            OrdenDeCompra firma = firmaOptional.get();

            // Update the estado field
            firma.setEstado("Firmado una vez");

            // Save the updated entity back to the repository
            firmaRepo.save(firma);
        } else {

        }
    }

    public void processSegundaFirma(Long solicitudId) {
        firmaRepo.updateEstadoToSignedIfoneSignature(solicitudId);

        Optional<OrdenDeCompra> firmaOptional = firmaRepo.findById(solicitudId);

        if (firmaOptional.isPresent()) {
            OrdenDeCompra firma = firmaOptional.get();

            // Update the estado field
            firma.setEstado("Firmado dos veces");

            // Save the updated entity back to the repository
            firmaRepo.save(firma);
        } else {

        }
    }

    public void processTerceraFirma(Long solicitudId) {
        firmaRepo.updateEstadoToSignedIfTwoSignature(solicitudId);

        Optional<OrdenDeCompra> firmaOptional = firmaRepo.findById(solicitudId);

        if (firmaOptional.isPresent()) {
            OrdenDeCompra firma = firmaOptional.get();

            // Update the estado field
            firma.setEstado("Firmado tres veces");

            // Save the updated entity back to the repository
            firmaRepo.save(firma);
        } else {

        }
    }

    public void processAprobado(Long solicitudId) {
        firmaRepo.updateEstadoToSignedIfThreeSignature(solicitudId);

        Optional<OrdenDeCompra> firmaOptional = firmaRepo.findById(solicitudId);

        if (firmaOptional.isPresent()) {
            OrdenDeCompra firma = firmaOptional.get();

            // Update the estado field
            firma.setEstado("Aprobado");

            // Save the updated entity back to the repository
            firmaRepo.save(firma);
        } else {

        }
    }

    public void solicitudRechazar(Long solicitudId, String motivoRechazo) {
        firmaRepo.updateEstadoToSignedIfRechazado(solicitudId, motivoRechazo);

        Optional<OrdenDeCompra> firmaOptional = firmaRepo.findById(solicitudId);

        if (firmaOptional.isPresent()) {
            OrdenDeCompra firma = firmaOptional.get();

            // Update the estado field
            firma.setEstado("Rechazado");

            // Save the updated entity back to the repository
            firmaRepo.save(firma);
        } else {

        }
    }

}