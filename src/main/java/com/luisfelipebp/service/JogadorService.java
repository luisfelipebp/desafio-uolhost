package com.luisfelipebp.service;

import com.luisfelipebp.model.DTO.JogadorDTO;
import com.luisfelipebp.model.DTO.VingadorDTO;
import com.luisfelipebp.model.Jogador;

import com.luisfelipebp.model.enums.GrupoEnum;
import com.luisfelipebp.repository.JogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JogadorService {

    @Autowired
    private JogadorRepository jogadorRepository;

    @Autowired
    private ConsumeJsonService consumeJsonService;

    @Autowired
    private ConsumeXmlService consumeXmlService;

    public Jogador findById(Long id) throws Exception {
        return jogadorRepository.findById(id).orElseThrow(() -> new Exception("Jogador não encontrado"));
    }

    public List<Jogador> findAll(){
        return jogadorRepository.findAll();
    }

    public void deleteById(Long id){
        jogadorRepository.deleteById(id);
    }

    public Jogador createJogador(JogadorDTO jogadorDTO) throws Exception {
        Jogador newJogador = new Jogador(jogadorDTO);

        if(jogadorDTO.grupo().equals(GrupoEnum.LIGA_DA_JUSTICA)){
            String codinomeLigaDaJustica = obterCodinomeLigaDaJustica();
            newJogador.setCodinome(codinomeLigaDaJustica);
            jogadorRepository.save(newJogador);
        }


        if(jogadorDTO.grupo().equals(GrupoEnum.VINGADORES)){
            String codinomeVingador = obterCodinomeVingadores();
            newJogador.setCodinome(codinomeVingador);
            jogadorRepository.save(newJogador);

        }

        return newJogador;
    }

    private String obterCodinomeLigaDaJustica() throws Exception {
        List<String> liga = consumeXmlService.consumeXml();

        int i = 0;
        if(liga.isEmpty()){
            throw new Exception("Falha ao carregar lista de codinomes");
        }
        String codinome = liga.get(i);
        while(jogadorRepository.findByCodinome(codinome) != null){
            if(i == liga.size() - 1){
                throw new Exception("Não existem mais usuários da liga da justiça disponíveis.");
            }
            i++;
            codinome = liga.get(i);
        }
        return codinome;
    }

    private String obterCodinomeVingadores() throws Exception {
        List<VingadorDTO> vingadores = consumeJsonService.consumeJson();

        int i = 0;
        if(vingadores.isEmpty()){
            throw new Exception("Falha ao carregar lista de codinomes");
        }
        String codinome = vingadores.get(i).codinome();
        while(jogadorRepository.findByCodinome(codinome) != null){
            if(i == vingadores.size() - 1){
                throw new Exception("Não existem mais usuários dos vingadores disponíveis.");
            }
            i++;
            codinome = vingadores.get(i).codinome();
    }
        return codinome;
    }

}

