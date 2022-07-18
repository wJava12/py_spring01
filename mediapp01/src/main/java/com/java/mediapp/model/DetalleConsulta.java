package com.java.mediapp.model;


import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "detalle_consulta")
public class DetalleConsulta {

    @Id
    @GeneratedValue
    private Integer idDetalleConsulta;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_consulta",nullable = false,foreignKey = @ForeignKey(name = "FK_consulta_detalle"))
    private Consulta consulta;

    @Column(name = "diagnostico",nullable = false,length = 70)
    private String diagnostico;

    @Column(name = "tratamiento",nullable = false,length = 300)
    private String tratamiento;

    public Integer getIdDetalleConsulta() {
        return idDetalleConsulta;
    }

    public void setIdDetalleConsulta(Integer idDetalleConsulta) {
        this.idDetalleConsulta = idDetalleConsulta;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }
}
