package br.com.model;

import br.com.model.Cliente;
import br.com.model.PacoteTarifas;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClienteTest {

    private Cliente cliente;

    @BeforeEach
    public void setup() {
        cliente = new Cliente();
    }

    @Test
    public void testId() {
        Long id = 1L;
        cliente.setId(id);

        Assertions.assertEquals(id, cliente.getId());
    }

    @Test
    public void testName() {
        String name = "John Doe";
        cliente.setName(name);

        Assertions.assertEquals(name, cliente.getName());
    }

    @Test
    public void testCpf() {
        String cpf = "123456789";
        cliente.setCpf(cpf);

        Assertions.assertEquals(cpf, cliente.getCpf());
    }

    @Test
    public void testDataNasc() {
        String dataNasc = "1990-01-01";
        cliente.setDataNasc(dataNasc);

        Assertions.assertEquals(dataNasc, cliente.getDataNasc());
    }

    @Test
    public void testPacoteTarifas() {
        PacoteTarifas pacoteTarifas = new PacoteTarifas();
        cliente.setPacoteTarifas(pacoteTarifas);

        Assertions.assertEquals(pacoteTarifas, cliente.getPacoteTarifas());
    }
}