package com.example.demo.dto;

import java.math.BigDecimal;

public class SolicitudUsuarioXDTO {
    private String nombre;
    private long id;
    private long solicitud_id;
    private long producto_id;
    private long cantidad;
    private String descripcion;
    private BigDecimal precio_unitario;
    private String destino;
    private String motivoRechazo;

    /**
     * @return String return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
     * @return long return the solicitud_id
     */
    public long getSolicitud_id() {
        return solicitud_id;
    }

    /**
     * @param solicitud_id the solicitud_id to set
     */
    public void setSolicitud_id(long solicitud_id) {
        this.solicitud_id = solicitud_id;
    }

    /**
     * @return long return the producto_id
     */
    public long getProducto_id() {
        return producto_id;
    }

    /**
     * @param producto_id the producto_id to set
     */
    public void setProducto_id(long producto_id) {
        this.producto_id = producto_id;
    }

    /**
     * @return long return the cantidad
     */
    public long getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return String return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return BigDecimal return the precio_unitario
     */
    public BigDecimal getPrecio_unitario() {
        return precio_unitario;
    }

    /**
     * @param precio_unitario the precio_unitario to set
     */
    public void setPrecio_unitario(BigDecimal precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    /**
     * @return String return the destino
     */
    public String getDestino() {
        return destino;
    }

    /**
     * @param destino the destino to set
     */
    public void setDestino(String destino) {
        this.destino = destino;
    }

    /**
     * @return String return the motivoRechazo
     */
    public String getMotivoRechazo() {
        return motivoRechazo;
    }

    /**
     * @param motivoRechazo the motivoRechazo to set
     */
    public void setMotivoRechazo(String motivoRechazo) {
        this.motivoRechazo = motivoRechazo;
    }

}