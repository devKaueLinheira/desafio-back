package br.com.web.rest;


import br.com.exceptions.ClienteException;
import br.com.model.Cliente;
import br.com.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
@Transactional
public class ClienteController {
    @Autowired
    private ClienteService clienteService;


    @Autowired
    public ClienteController(ClienteService service) {
        this.clienteService = service;
    }

    @GetMapping
    public List<Cliente> listarTodos() {
        return clienteService.listarTodos();
    }

    @ResponseBody
    @GetMapping("/consultar/{id}")
    public Cliente listarClineteId(@PathVariable("id") Long id){
        Cliente buscaClinete = clienteService.findById(id)
                .orElseThrow(()-> new ClienteException("Cliente não encontrado pelo id informado: " + id));
        return buscaClinete;

    }
    @PostMapping
    @Transactional
    @ResponseBody
    public Cliente saveCustomer(@Valid @RequestBody Cliente customer){
        clienteService.saveCustomer(customer);
        return customer;
    }

    @ResponseBody
    @Transactional
    @PutMapping("/atualizar/{id}")
    public Cliente atualizarCliente(@PathVariable Long id, @Valid @RequestBody Cliente cliente){
        Cliente clienteAtualizado = clienteService.findById(id)
                .orElseThrow(() -> new ClienteException("Cliente não encontrado pelo id informado: " + id));
        clienteAtualizado.setName(cliente.getName());
        clienteAtualizado.setCpf(cliente.getCpf());
        clienteAtualizado.setDataNasc(cliente.getDataNasc());
        clienteService.updateCustomer(id, cliente);
        return clienteAtualizado;
    }
    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deletarCliente(@PathVariable Long id, @Valid Cliente cliente){
        Cliente clienteDeletado = clienteService.findById(id)
                .orElseThrow(() -> new ClienteException("Cliente não encontrado pelo id informado: " + id));
        clienteService.deleteCustomer(cliente);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deletado", Boolean.TRUE);
        return response;
    }
}
