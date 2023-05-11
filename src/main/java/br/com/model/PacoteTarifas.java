package br.com.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class PacoteTarifas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private BigDecimal valorMaximo;

    private BigDecimal valorMinimo;

    @OneToMany()
    private List<Cliente> clientes = new ArrayList<>();

    public PacoteTarifas() {
    }
    public PacoteTarifas(String nome, BigDecimal valorMinimo, BigDecimal valorMaximo) {
        this.nome = nome;
        this.valorMinimo = valorMinimo;
        this.valorMaximo = valorMaximo;
    }

    public PacoteTarifas(Long id, String nome, BigDecimal valorMinimo, BigDecimal valorMaximo, List<Cliente> clientes) {
        this.id = id;
        this.nome = nome;
        this.valorMinimo = valorMinimo;
        this.valorMaximo = valorMaximo;
        this.clientes = clientes;
    }

}