package br.com.service;


import br.com.exceptions.PacoteTarifasException;
import br.com.model.Cliente;
import br.com.model.PacoteTarifas;
import br.com.repository.ClienteRepository;
import br.com.repository.PacoteTarifasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    @Autowired
    private PacoteTarifasRepository pacoteTarifasRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository){this.clienteRepository= clienteRepository;
    }

    public List<Cliente> listarTodos(){
        return clienteRepository.findAllByOrderByNameAsc();
    }
    public ResponseEntity<Page<Cliente>> listarTodasPaginas(Pageable pageable){
        return new ResponseEntity<>(clienteRepository.findAll(pageable), HttpStatus.OK);
    }

    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id);
    }

    public ResponseEntity<?> saveCustomer(Cliente cliente){

            cliente.setName(cliente.getName());
            cliente.setCpf(cliente.getCpf());
            cliente.setDataNasc(cliente.getDataNasc());
            clienteRepository.save(cliente);
            return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<?> updateCustomer(Long id, Cliente cliente){
        clienteRepository.findById(id);
        cliente.setName(cliente.getName());
        cliente.setCpf(cliente.getCpf());
        cliente.setDataNasc(cliente.getDataNasc());
        clienteRepository.save(cliente);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> deleteCustomer(Cliente cliente) {

        clienteRepository.delete(cliente);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }

}
