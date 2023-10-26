package com.example.mapeamento.mapeamento.model.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.List;

@Entity
@DiscriminatorValue("M")
public class Medico extends Pessoa {
    @NotBlank(message = "CRM é obrigatório")
    private String crm;
    @OneToMany(mappedBy = "medico")
    @Valid
    private List<Consulta> consultas;
    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }

    public String dadosMedico(){
        return " CRM: " + this.crm + " Consultas: " + this.consultas;
    }
}
