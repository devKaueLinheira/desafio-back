package br.com.repository;

import br.com.model.PacoteTarifas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacoteTarifasRepository extends JpaRepository<PacoteTarifas, Long> {

    List<PacoteTarifas> findByNome(String nome);

    List<PacoteTarifas> findByNomeAndId(String nome, Long id);
}