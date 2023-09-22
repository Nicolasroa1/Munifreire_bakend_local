package com.example.demo.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Entity
@Table(name = "producto", schema = "public")
public class Producto implements Serializable {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private long id;
    private String descripcion;
    private BigDecimal precio_unitario;
    private long proveedor_id;

    public Producto() {

    }

    public Producto(String descripcion, BigDecimal precio_unitario, long proveedor_id) {
        this.descripcion = descripcion;
        this.precio_unitario = precio_unitario;
        this.proveedor_id = proveedor_id;
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
     * @return long return the proveedor_id
     */
    public long getProveedor_id() {
        return proveedor_id;
    }

    /**
     * @param proveedor_id the proveedor_id to set
     */
    public void setProveedor_id(long proveedor_id) {
        this.proveedor_id = proveedor_id;
    }

}