package com.luisfelipebp.model;

import com.luisfelipebp.model.DTO.JogadorDTO;
import com.luisfelipebp.model.enums.GrupoEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "players")
public class Jogador {

    public Jogador(JogadorDTO jogadorDTO){
        this.id = jogadorDTO.id();
        this.nome = jogadorDTO.nome();
        this.email = jogadorDTO.email();
        this.telefone = jogadorDTO.telefone();
        this.grupo = jogadorDTO.grupo();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    private String telefone;

    @Enumerated(EnumType.STRING)
    private GrupoEnum grupo;

    private String codinome;
}
