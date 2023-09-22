package com.example.demo.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.repo.DocumentoRepo;
import com.example.demo.repo.SolicitudRepo;
import com.example.demo.dto.ProductosSeleccionadosDTO;
import com.example.demo.dto.SolicitudUsuarioXDTO;
import com.example.demo.model.Documento;
import com.example.demo.model.OrdenDeCompra;
import com.example.demo.model.OrdenDeCompraSolicitud;

import com.example.demo.model.SolicitudProducto;
import java.sql.Timestamp;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.time.LocalDate;

@Service
public class SolicitudService {

    @Autowired
    private final SolicitudRepo solicitudRepo;

    @Autowired
    private DocumentoRepo documentoRepo;

    public SolicitudService(SolicitudRepo solicitudRepo) {
        this.solicitudRepo = solicitudRepo;
    }

    public OrdenDeCompraSolicitud addOrdenDeCompra(OrdenDeCompraSolicitud ordenDeCompraSolicitud,
            List<SolicitudProducto> solicitudProductos, long currentUserId, String departamento, String destino) {
        OrdenDeCompraSolicitud savedOrdenDeCompra = solicitudRepo.save(ordenDeCompraSolicitud);
        Long savedOrdenDeCompraId = savedOrdenDeCompra.getId();
        OrdenDeCompra ordenDeCompra = new OrdenDeCompra();
        Date today = Date.valueOf(LocalDate.now());
        Timestamp timestamp = new Timestamp(today.getTime());
        ordenDeCompra.setDocumento_id(1);
        ordenDeCompra.setFecha_solicitud(timestamp);
        // cambiar por el usuario de verdad
        ordenDeCompra.setUsuario_solicitud(currentUserId);
        ordenDeCompra.setOrden_de_compra_producto_solicitud_id(savedOrdenDeCompraId);
        ordenDeCompra.setEstado("pendiente");
        ordenDeCompra.setDepartamento(departamento);
        ordenDeCompra.setDestino(destino);
        solicitudRepo.save(ordenDeCompra);
        for (SolicitudProducto solicitudProducto : solicitudProductos) {
            solicitudProducto.setSolicitud_id(savedOrdenDeCompraId);
            solicitudRepo.save(solicitudProducto);
        }
        return savedOrdenDeCompra;
    }

    public List<Object[]> getAllSolicitudes(String estado, String departamento) {
        if (estado.equals("pendiente")) {
            return solicitudRepo.getSolicitudesAprobadorUno(estado, departamento);

        } else {
            return solicitudRepo.getSolicitudes(estado);
        }

    }

    public List<Object[]> getAllSolicitudesByUserId(Long id) {
        return solicitudRepo.getSolicitudesByUserId(id);
    }

    public List<SolicitudUsuarioXDTO> findOrdenDeCompraById(Long id) {
        List<Object[]> orden = solicitudRepo.findOrdenDeCompraById(id);

        List<SolicitudUsuarioXDTO> solicitudList = new ArrayList<>();
        for (Object[] result : orden) {
            SolicitudUsuarioXDTO dto = new SolicitudUsuarioXDTO();
            dto.setNombre((String) result[0]);
            dto.setId(((Number) result[1]).longValue());
            dto.setSolicitud_id(((Number) result[2]).longValue());
            dto.setProducto_id(((Number) result[3]).longValue());
            dto.setCantidad(((Number) result[4]).longValue());
            dto.setDescripcion((String) result[5]);
            dto.setPrecio_unitario((BigDecimal) result[6]);
            dto.setDestino((String) result[7]);
            dto.setMotivoRechazo((String) result[8]);
            solicitudList.add(dto);
        }

        return solicitudList;
    }

    public OrdenDeCompraSolicitud processOrdenDeCompra(
            List<ProductosSeleccionadosDTO> productosSeleccionados, long currentUserId, String departamento,
            String destino) {
        int totalCantidad = 0; // initialize the totalCantidad variable
        BigDecimal totalPrecio = BigDecimal.ZERO;
        BigDecimal constant = new BigDecimal("1.19");
        List<SolicitudProducto> solicitudProductos = new ArrayList<>();
        for (ProductosSeleccionadosDTO productos : productosSeleccionados) {
            totalCantidad += productos.getCantidad(); // add the cantidad value to the totalCantidad variable
            // add the precio_unitario to the totalPrecio
            // Multiply the cantidad by precioUnitario and add to totalPrecio
            BigDecimal cantidad = new BigDecimal(productos.getCantidad());
            BigDecimal precioUnitario = productos.getPrecio_unitario();
            BigDecimal subtotal = precioUnitario.multiply(cantidad);
            totalPrecio = totalPrecio.add(subtotal);

            SolicitudProducto solicitudProducto = new SolicitudProducto();
            solicitudProducto.setProducto_id(productos.getId());
            solicitudProducto.setCantidad(productos.getCantidad()); // set the cantidad value
            solicitudProductos.add(solicitudProducto); // add the new SolicitudProducto object to the list

        }

        BigDecimal totalPrecio_iva = totalPrecio.divide(constant, RoundingMode.HALF_UP);
        BigDecimal totalPrecio_neto = totalPrecio.subtract(totalPrecio_iva);
        Date today = Date.valueOf(LocalDate.now());
        OrdenDeCompraSolicitud ordenDeCompraSolicitud = new OrdenDeCompraSolicitud();
        ordenDeCompraSolicitud.setCantidadtotal(totalCantidad);
        ordenDeCompraSolicitud.setTotal(totalPrecio);
        ordenDeCompraSolicitud.setTotal_neto(totalPrecio_neto);
        ordenDeCompraSolicitud.setTotal_iva(totalPrecio_iva);
        ordenDeCompraSolicitud.setTotal_total(totalPrecio);
        ordenDeCompraSolicitud.setTipo_de_compra_id(1);
        ordenDeCompraSolicitud.setDetalle_solicitud(destino);
        ordenDeCompraSolicitud.setFecha_creacion(today);
        ordenDeCompraSolicitud.setFecha_solicitud(today);
        OrdenDeCompraSolicitud savedOrdenDeCompra = addOrdenDeCompra(ordenDeCompraSolicitud,
                solicitudProductos, currentUserId, departamento, destino);
        return (OrdenDeCompraSolicitud) savedOrdenDeCompra;
    }

    public Documento uploadFile(MultipartFile file) throws IOException {
        byte[] data = file.getBytes();
        Documento documento = new Documento();
        documento.setDocumento(data);
        return documentoRepo.save(documento);

    }

}