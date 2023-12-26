package com.luisfelipebp.model.DTO;

import com.luisfelipebp.model.enums.GrupoEnum;

public record JogadorDTO(Long id, String nome, String email, String telefone, GrupoEnum grupo){
}
