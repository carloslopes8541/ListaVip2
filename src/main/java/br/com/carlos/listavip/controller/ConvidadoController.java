package br.com.carlos.listavip.controller;

import br.com.carlos.listavip.EnviarEmail;
import br.com.carlos.listavip.entity.Convidado;
import br.com.carlos.listavip.repository.ConvidadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class ConvidadoController {

    @Autowired
    public ConvidadoRepository repository;

    @GetMapping("/convidados")
    public List<Convidado> obterTodos() {
        return repository.findAll();
    }

    @GetMapping("{/convidados/{id}")
    public Convidado buscarPorId(@PathVariable Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, " Convidado não encontrado"));
    }


    @PostMapping("/convidados")
    @ResponseStatus(HttpStatus.CREATED)
    public Convidado salvar(@RequestBody Convidado convidado) {
        return repository.save(convidado);
    }

    @PutMapping("/convidados/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody Convidado convidadoAtualizado) {
        repository.findById(id)
                .map(convidado -> {
                    convidado.setNome(convidadoAtualizado.getNome());
                    convidado.setEmail(convidadoAtualizado.getEmail());
                    convidado.setTelefone(convidadoAtualizado.getTelefone());
                    convidado.setCpf(convidadoAtualizado.getCpf());
                    return repository.save(convidadoAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Convidado não encontrado"));
    }


    @DeleteMapping("/convidados/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id) {
        repository
                .findById(id)
                .map(convidado -> {
                    repository.delete(convidado);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Convidado não encontrado"));
    }

}
