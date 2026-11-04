package com.tecsup.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(nullable = false)
    private Integer duracion;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(name = "id_docente", nullable = false)
    private Long idDocente;

    @Column(nullable = false, length = 20)
    private String estado;

    public Curso() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Integer getDuracion() { return duracion; }
    public void setDuracion(Integer duracion) { this.duracion = duracion; }

    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }

    public Long getIdDocente() { return idDocente; }
    public void setIdDocente(Long idDocente) { this.idDocente = idDocente; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}