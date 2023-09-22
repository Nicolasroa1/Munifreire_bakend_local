package com.example.demo.repo;

import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.model.OrdenDeCompra;

@Repository
public interface FirmaRepo extends JpaRepository<OrdenDeCompra, Long> {

    @Modifying
    @Query(value = "UPDATE public.orden_de_compra SET estado = 'estado_primera_firma', firmas ="
            + "array_append(firmas, 'PersonalQRGoesHere') WHERE orden_de_compra_producto_solicitud_id = ?1" +
            "AND estado = 'pendiente'", nativeQuery = true)
    void updateEstadoToSignedIfPending(Long solicitudId);

    @Modifying
    @Query(value = "UPDATE public.orden_de_compra SET estado = 'estado_segunda_firma', firmas = array_append(firmas, 'QRFIRMA2') WHERE orden_de_compra_producto_solicitud_id = ?1 AND estado = 'estado_primera_firma'", nativeQuery = true)
    void updateEstadoToSignedIfoneSignature(Long solicitudId);

    @Modifying
    @Query(value = "UPDATE public.orden_de_compra SET estado = 'estado_tercera_firma', firmas = array_append(firmas, 'QRFIRMA3') WHERE orden_de_compra_producto_solicitud_id = ?1 AND estado = 'estado_segunda_firma'", nativeQuery = true)
    void updateEstadoToSignedIfTwoSignature(Long solicitudId);

    @Modifying
    @Query(value = "UPDATE public.orden_de_compra SET estado = 'aprobado' WHERE orden_de_compra_producto_solicitud_id = ?1 AND estado = 'estado_tercera_firma'", nativeQuery = true)
    void updateEstadoToSignedIfThreeSignature(Long solicitudId);

    @Modifying
    @Query(value = "UPDATE public.orden_de_compra SET estado = 'rechazado', motivo_rechazo = ?2 WHERE orden_de_compra_producto_solicitud_id = ?1", nativeQuery = true)
    void updateEstadoToSignedIfRechazado(Long solicitudId, String motivoRechazo);

}