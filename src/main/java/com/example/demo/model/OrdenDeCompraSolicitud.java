package com.example.demo.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Entity
@Table(name = "orden_de_compra_producto_solicitud", schema = "public")
public class OrdenDeCompraSolicitud implements Serializable {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private long id;
    private long cantidadtotal;
    private BigDecimal total;
    private BigDecimal total_neto;
    private BigDecimal total_iva;
    private BigDecimal total_total;
    private Date fecha_creacion;
    private long tipo_de_compra_id;
    private String detalle_solicitud;
    private Date fecha_solicitud;

    public OrdenDeCompraSolicitud() {

    }

    public OrdenDeCompraSolicitud(long id, long producto_id, long cantidadTotal, BigDecimal total,
            BigDecimal total_neto,
            BigDecimal total_iva,
            BigDecimal total_total, Date fecha_creacion, long tipo_de_compra_id, String detalle_solicitud,
            Date fecha_solicitud) {
        this.id = id;
        this.cantidadtotal = cantidadTotal;
        this.total = total;
        this.total_neto = total_neto;
        this.total_iva = total_iva;
        this.total_total = total_total;
        this.fecha_creacion = fecha_creacion;
        this.tipo_de_compra_id = tipo_de_compra_id;
        this.detalle_solicitud = detalle_solicitud;
        this.fecha_solicitud = fecha_solicitud;

    }

    /**
     * @return long return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return long return the cantidad
     */
    public long getCantidadtotal() {
        return cantidadtotal;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidadtotal(long cantidadTotal) {
        this.cantidadtotal = cantidadTotal;
    }

    /**
     * @return BigDecimal return the total
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /**
     * @return BigDecimal return the total_neto
     */
    public BigDecimal getTotal_neto() {
        return total_neto;
    }

    /**
     * @param total_neto the total_neto to set
     */
    public void setTotal_neto(BigDecimal total_neto) {
        this.total_neto = total_neto;
    }

    /**
     * @return BigDecimal return the total_iva
     */
    public BigDecimal getTotal_iva() {
        return total_iva;
    }

    /**
     * @param total_iva the total_iva to set
     */
    public void setTotal_iva(BigDecimal total_iva) {
        this.total_iva = total_iva;
    }

    /**
     * @return BigDecimal return the total_total
     */
    public BigDecimal getTotal_total() {
        return total_total;
    }

    /**
     * @param total_total the total_total to set
     */
    public void setTotal_total(BigDecimal total_total) {
        this.total_total = total_total;
    }

    /**
     * @return Date return the fecha_creacion
     */
    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    /**
     * @param fecha_creacion the fecha_creacion to set
     */
    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    /**
     * @return long return the tipo_de_compra_id
     */
    public long getTipo_de_compra_id() {
        return tipo_de_compra_id;
    }

    /**
     * @param tipo_de_compra_id the tipo_de_compra_id to set
     */
    public void setTipo_de_compra_id(long tipo_de_compra_id) {
        this.tipo_de_compra_id = tipo_de_compra_id;
    }

    /**
     * @return String return the detalle_solicitud
     */
    public String getDetalle_solicitud() {
        return detalle_solicitud;
    }

    /**
     * @param detalle_solicitud the detalle_solicitud to set
     */
    public void setDetalle_solicitud(String detalle_solicitud) {
        this.detalle_solicitud = detalle_solicitud;
    }

    /**
     * @return Date return the fecha_solicitud
     */
    public Date getFecha_solicitud() {
        return fecha_solicitud;
    }

    /**
     * @param fecha_solicitud the fecha_solicitud to set
     */
    public void setFecha_solicitud(Date fecha_solicitud) {
        this.fecha_solicitud = fecha_solicitud;
    }

}