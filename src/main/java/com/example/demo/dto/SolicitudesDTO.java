package com.example.demo.dto;

import java.sql.Timestamp;

public class SolicitudesDTO {
    private String nombre;
    private long id;
    private String fecha_solicitud;
    private String estado;

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
     * @return String return the fecha_solicitud
     */
    public String getFecha_solicitud() {
        return fecha_solicitud;
    }

    /**
     * @param formattedDate the fecha_solicitud to set
     */
    public void setFecha_solicitud(String formattedDate) {
        this.fecha_solicitud = formattedDate;
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

}