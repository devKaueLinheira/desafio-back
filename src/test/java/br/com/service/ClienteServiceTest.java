package br.com.service;

import br.com.exceptions.PacoteTarifasException;
import br.com.model.Cliente;
import br.com.repository.ClienteRepository;
import br.com.repository.PacoteTarifasRepository;
import br.com.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private PacoteTarifasRepository pacoteTarifasRepository;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente cliente;

    @BeforeEach
    public void setup() {
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setName("João");
        cliente.setCpf("12345678901");
        cliente.setDataNasc("01/01/2000");
    }

    @Test
    public void testListarTodos() {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(cliente);
        when(clienteRepository.findAllByOrderByNameAsc()).thenReturn(clientes);

        List<Cliente> result = clienteService.listarTodos();

        assertEquals(clientes.size(), result.size());
        assertEquals(clientes.get(0).getId(), result.get(0).getId());
        assertEquals(clientes.get(0).getName(), result.get(0).getName());
        assertEquals(clientes.get(0).getCpf(), result.get(0).getCpf());
        assertEquals(clientes.get(0).getDataNasc(), result.get(0).getDataNasc());
    }

    @Test
    public void testFindById() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        Optional<Cliente> result = clienteService.findById(1L);

        assertEquals(cliente.getId(), result.get().getId());
        assertEquals(cliente.getName(), result.get().getName());
        assertEquals(cliente.getCpf(), result.get().getCpf());
        assertEquals(cliente.getDataNasc(), result.get().getDataNasc());
    }

    @Test
    public void testSaveCustomer() {
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        ResponseEntity<?> result = clienteService.saveCustomer(cliente);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }

    @Test
    public void testUpdateCustomer() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        ResponseEntity<?> result = clienteService.updateCustomer(1L, cliente);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void testDeleteCustomer() {
        ResponseEntity<?> result = clienteService.deleteCustomer(cliente);

        assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
    }

}
