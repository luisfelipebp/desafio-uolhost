package com.luisfelipebp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.luisfelipebp.model.DTO.JogadorDTO;
import com.luisfelipebp.model.Jogador;
import com.luisfelipebp.service.JogadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jogador")
public class JogadorController {

    @Autowired
    private JogadorService jogadorService;

    @GetMapping("/todos")
    public ResponseEntity<List<Jogador>> findAll(){
        return ResponseEntity.ok().body(jogadorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jogador> findById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok().body(jogadorService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Jogador> createJogador(@RequestBody JogadorDTO jogadorDTO) throws Exception {
        Jogador jogador = jogadorService.createJogador(jogadorDTO);
        return ResponseEntity.status(201).body(jogador);
    }
}
