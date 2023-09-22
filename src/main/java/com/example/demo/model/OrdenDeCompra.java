package com.example.demo.model;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Entity
@Table(name = "orden_de_compra", schema = "public")
public class OrdenDeCompra implements Serializable {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private long id;
    private long usuario_solicitud;
    private long documento_id;
    private long orden_de_compra_producto_solicitud_id;
    private Timestamp fecha_solicitud;
    private String estado;
    private String departamento;
    private String destino;

    public OrdenDeCompra() {

    }

    public OrdenDeCompra(long id, long usuario_solicitud, long documento_id, long orden_de_compra_producto_solicitud_id,
            Timestamp fecha_solicitud, String estado, String departamento, String destino) {
        this.id = id;
        this.usuario_solicitud = usuario_solicitud;
        this.documento_id = documento_id;
        this.orden_de_compra_producto_solicitud_id = orden_de_compra_producto_solicitud_id;
        this.fecha_solicitud = fecha_solicitud;
        this.estado = estado;
        this.departamento = departamento;
        this.destino = destino;

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
     * @return long return the usuario_solicitud
     */
    public long getUsuario_solicitud() {
        return usuario_solicitud;
    }

    /**
     * @param usuario_solicitud the usuario_solicitud to set
     */
    public void setUsuario_solicitud(long usuario_solicitud) {
        this.usuario_solicitud = usuario_solicitud;
    }

    /**
     * @return long return the documento_id
     */
    public long getDocumento_id() {
        return documento_id;
    }

    /**
     * @param documento_id the documento_id to set
     */
    public void setDocumento_id(long documento_id) {
        this.documento_id = documento_id;
    }

    /**
     * @return long return the orden_de_compra_producto_solicitud_id
     */
    public long getOrden_de_compra_producto_solicitud_id() {
        return orden_de_compra_producto_solicitud_id;
    }

    /**
     * @param orden_de_compra_producto_solicitud_id the
     *                                              orden_de_compra_producto_solicitud_id
     *                                              to set
     */
    public void setOrden_de_compra_producto_solicitud_id(long orden_de_compra_producto_solicitud_id) {
        this.orden_de_compra_producto_solicitud_id = orden_de_compra_producto_solicitud_id;
    }

    /**
     * @return Date return the fecha_solicitud
     */
    public Timestamp getFecha_solicitud() {
        return fecha_solicitud;
    }

    /**
     * @param fecha_solicitud the fecha_solicitud to set
     */
    public void setFecha_solicitud(Timestamp fecha_solicitud) {
        this.fecha_solicitud = fecha_solicitud;
    }

    /**
     * @return String return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return String return the departamento
     */
    public String getDepartamento() {
        return departamento;
    }

    /**
     * @param departamento the departamento to set
     */
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
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

}