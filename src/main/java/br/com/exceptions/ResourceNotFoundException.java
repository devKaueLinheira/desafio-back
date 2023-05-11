package br.com.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    private String nome;
    private String cpf;
    private LocalDate dataNascimento;

    public ResourceNotFoundException(String nome, String cpf, LocalDate dataNascimento ){
        super(String.format("%s not found with %s : '%s'", nome, cpf, dataNascimento));
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }
}
