package com.luisfelipebp.model.DTO;

import com.luisfelipebp.model.enums.GrupoEnum;

public record JogadorDTO(String nome, String email, String telefone, GrupoEnum grupo){
}
