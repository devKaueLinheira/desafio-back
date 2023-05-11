package br.com.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;

    private String cpf;

    private String dataNasc;


    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private PacoteTarifas pacoteTarifas;

    public Cliente() {
    }

    public Cliente (String nome, String cpf, String dataNascimento) {
        this.name = nome;
        this.cpf = cpf;
        this.dataNasc = dataNascimento;
    }

    public Cliente(Long id, String nome, String cpf, String dataNascimento, PacoteTarifas pacoteTarifas) {
        this.Id = id;
        this.name = nome;
        this.cpf = cpf;
        this.dataNasc = dataNascimento;
        this.pacoteTarifas = pacoteTarifas;
    }

}
