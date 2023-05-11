package br.com.repository;


import br.com.model.Cliente;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends PagingAndSortingRepository<Cliente, Long> {

    List<Cliente> findAllByOrderByNameAsc();

    Optional<Cliente> findByCpf(String cpf);
}
