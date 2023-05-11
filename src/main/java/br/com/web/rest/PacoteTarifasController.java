package br.com.web.rest;

import br.com.exceptions.PacoteTarifasException;
import br.com.model.PacoteTarifas;
import br.com.repository.PacoteTarifasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacotes-tarifas")
public class PacoteTarifasController {
    @Autowired
    private PacoteTarifasRepository repository;

    @GetMapping
    public List<PacoteTarifas> listarPacotesTarifas(@RequestParam(required = false) String nome, @RequestParam(required = false) Long id) {
        if (nome != null && id != null) {
            return repository.findByNomeAndId(nome, id);
        } else if (nome != null) {
            return repository.findByNome(nome);
        } else if (id != null) {
            Optional<PacoteTarifas> optional = repository.findById(id);
            if (optional.isPresent()) {
                return Arrays.asList(optional.get());
            } else {
                return new ArrayList<>();
            }
        } else {
            return repository.findAll();
        }
    }
    @ResponseBody
    @GetMapping("/consulta/{id}")
    public PacoteTarifas consultarPctTarifasId(@PathVariable("id") Long id){
        PacoteTarifas buscarPctId = repository.findById(id)
                .orElseThrow(()-> new PacoteTarifasException("Pacote de tarifa não encontrado com o id: " + id ));
        return buscarPctId;
    }

    @PostMapping
    public PacoteTarifas criarPacoteTarifas(@RequestBody PacoteTarifas pacoteTarifas) {
        return repository.save(pacoteTarifas);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<PacoteTarifas> atualizarPacoteTarifas(@PathVariable Long id, @RequestBody PacoteTarifas pacoteTarifas) {
        Optional<PacoteTarifas> optional = repository.findById(id);
        if (optional.isPresent()) {
            PacoteTarifas pacoteTarifasExistente = optional.get();
            pacoteTarifasExistente.setNome(pacoteTarifas.getNome());
            pacoteTarifasExistente.setValorMaximo(pacoteTarifas.getValorMaximo());
            pacoteTarifasExistente.setValorMinimo(pacoteTarifas.getValorMinimo());
            repository.save(pacoteTarifasExistente);
            return ResponseEntity.ok(pacoteTarifasExistente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> excluirPacoteTarifas(@PathVariable Long id) {
        Optional<PacoteTarifas> optional = repository.findById(id);
        if (optional.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}