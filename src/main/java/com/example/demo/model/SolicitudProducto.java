package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Entity
@Table(name = "solicitud_producto", schema = "public")
public class SolicitudProducto implements Serializable {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private long id;
    private long solicitud_id;
    private long producto_id;
    private long cantidad;

    public SolicitudProducto() {

    }

    public SolicitudProducto(long solicitud_id, long producto_id, long cantidad) {
        this.solicitud_id = solicitud_id;
        this.producto_id = producto_id;
        this.cantidad = cantidad;
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

}