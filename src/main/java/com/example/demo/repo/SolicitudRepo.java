package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.OrdenDeCompra;
import com.example.demo.model.OrdenDeCompraSolicitud;
import com.example.demo.model.SolicitudProducto;

import java.util.*;

@Repository
public interface SolicitudRepo extends JpaRepository<OrdenDeCompra, Long> {
        OrdenDeCompraSolicitud save(OrdenDeCompraSolicitud ordenDeCompra);

        OrdenDeCompra save(OrdenDeCompra ordenDeCompra);

        @Query(nativeQuery = true, value = "SELECT u.name, sp.*, p.descripcion, p.precio_unitario, o.destino,o.motivo_rechazo "
                        +
                        "FROM public.users u " +
                        "JOIN public.orden_de_compra o ON u.id = o.usuario_solicitud " +
                        "JOIN public.solicitud_producto sp ON sp.solicitud_id = o.orden_de_compra_producto_solicitud_id "
                        +
                        "JOIN public.producto p ON p.id = sp.producto_id " +
                        "WHERE o.orden_de_compra_producto_solicitud_id = :id")
        List<Object[]> findOrdenDeCompraById(@Param("id") Long id);

        SolicitudProducto save(SolicitudProducto solicitudProducto);

        @Query(value = "SELECT ocps.id, ocps.fecha_solicitud, u.name, oc.estado " +
                        "FROM public.orden_de_compra oc " +
                        "JOIN public.orden_de_compra_producto_solicitud ocps ON oc.orden_de_compra_producto_solicitud_id = ocps.id "
                        +
                        "JOIN public.users u ON oc.usuario_solicitud = u.id " +
                        "WHERE oc.estado = :estado " +
                        "AND oc.departamento = :departamento", nativeQuery = true)
        List<Object[]> getSolicitudesAprobadorUno(@Param("estado") String estado,
                        @Param("departamento") String departamento);

        @Query(value = "SELECT ocps.id, ocps.fecha_solicitud, u.name, oc.estado " +
                        "FROM public.orden_de_compra oc " +
                        "JOIN public.orden_de_compra_producto_solicitud ocps ON oc.orden_de_compra_producto_solicitud_id = ocps.id "
                        +
                        "JOIN public.users u ON oc.usuario_solicitud = u.id " +
                        "WHERE oc.estado = :estado", nativeQuery = true)
        List<Object[]> getSolicitudes(@Param("estado") String estado);

        @Query(value = "SELECT ocps.id, ocps.fecha_solicitud, u.name,oc.estado " +
                        "FROM public.orden_de_compra oc " +
                        "JOIN public.orden_de_compra_producto_solicitud ocps " +
                        "   ON oc.orden_de_compra_producto_solicitud_id = ocps.id " +
                        "JOIN public.users u " +
                        "   ON oc.usuario_solicitud = u.id " +
                        "WHERE u.id = :id " +
                        "AND oc.estado NOT IN ('aprobado', 'rechazado')", nativeQuery = true)
        List<Object[]> getSolicitudesByUserId(@Param("id") Long id);
}