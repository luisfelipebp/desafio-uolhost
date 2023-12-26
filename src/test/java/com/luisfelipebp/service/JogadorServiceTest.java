package com.luisfelipebp.service;

import com.luisfelipebp.model.DTO.JogadorDTO;
import com.luisfelipebp.model.DTO.VingadorDTO;
import com.luisfelipebp.model.Jogador;
import com.luisfelipebp.model.enums.GrupoEnum;
import com.luisfelipebp.repository.JogadorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JogadorServiceTest {

    @Mock
    private JogadorRepository jogadorRepository;

    @Mock
    private ConsumeJsonService consumeJsonService;

    @Mock
    private ConsumeXmlService consumeXmlService;


    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @InjectMocks
    @Autowired
    private JogadorService jogadorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    @DisplayName("Deve salvar jogador do tipo vingador quando o consumo do json retornar um codinome")
    void deveSalvarJogadorDoTipoVingador() throws Exception {
        JogadorDTO jogadorDTO = new JogadorDTO(1L, "Luis", "luis@gmail.com", "999999999", GrupoEnum.VINGADORES);
        Jogador jogador = new Jogador(jogadorDTO);

        when(jogadorRepository.findById(1L)).thenReturn(Optional.of(jogador));

        when(consumeJsonService.consumeJson()).thenReturn(Arrays.asList(new VingadorDTO("Hulk")));


        var output = jogadorService.createJogador(jogadorDTO);
        jogador.setCodinome("Hulk");

        verify(jogadorRepository, times(1)).save(jogador);
        assertNotNull(output);
    }

    @Test
    @DisplayName("Deve salvar jogador do tipo liga da justiça quando o consumo do xml retornar um codinome")
    void deveSalvarJogadorDoTipoLigaDaJustica() throws Exception {
        JogadorDTO jogadorDTO = new JogadorDTO(2L, "Felipe", "felipe@gmail.com", "999999999", GrupoEnum.LIGA_DA_JUSTICA);
        Jogador jogador = new Jogador(jogadorDTO);

        when(jogadorRepository.findById(2L)).thenReturn(Optional.of(jogador));

        when(consumeXmlService.consumeXml()).thenReturn(Arrays.asList("Lanterna Verde"));

        var output = jogadorService.createJogador(jogadorDTO);
        jogador.setCodinome("Lanterna Verde");

        verify(jogadorRepository, times(1)).save(jogador);
        assertNotNull(output);
    }


    @Test
    @DisplayName("Deve lançar uma exceção quando o consumo do json não retornar um codinome")
    void deveLancarExcecaoQuandoJsonNaoRetornarJogador() throws Exception {
        JogadorDTO jogadorDTO = new JogadorDTO(1L, "Luis", "luis@gmail.com", "999999999", GrupoEnum.VINGADORES);
        Jogador jogador = new Jogador(jogadorDTO);

        when(jogadorRepository.findById(1L)).thenReturn(Optional.of(jogador));

        when(consumeJsonService.consumeJson()).thenReturn(Arrays.asList());

        Exception jogadorNaoEncontrado = Assertions.assertThrows(Exception.class, () -> {
            jogadorService.createJogador(jogadorDTO);
        });

        Assertions.assertEquals("Falha ao carregar lista de codinomes", jogadorNaoEncontrado.getMessage());
    }

    @Test
    @DisplayName("Deve lançar uma exceção quando o consumo do xml não retornar um codinome")
    void deveLancarExcecaoQuandoXmlNaoRetornarJogador() throws Exception {
        JogadorDTO jogadorDTO = new JogadorDTO(2L, "Felipe", "felipe@gmail.com", "999999999", GrupoEnum.LIGA_DA_JUSTICA);
        Jogador jogador = new Jogador(jogadorDTO);

        when(jogadorRepository.findById(2L)).thenReturn(Optional.of(jogador));

        when(consumeXmlService.consumeXml()).thenReturn(Arrays.asList());

        Exception jogadorNaoEncontrado = Assertions.assertThrows(Exception.class, () -> {
            jogadorService.createJogador(jogadorDTO);
        });

        Assertions.assertEquals("Falha ao carregar lista de codinomes", jogadorNaoEncontrado.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando não existir codinomes disponíveis")
    void deveLancarExcecaoQuandoNaoExistirCodinomesDisponiveis() throws Exception {
        JogadorDTO jogadorDTO = new JogadorDTO(2L, "Felipe", "felipe@gmail.com", "999999999", GrupoEnum.LIGA_DA_JUSTICA);
        Jogador jogador = new Jogador(jogadorDTO);

        when(jogadorRepository.findById(2L)).thenReturn(Optional.of(jogador));

        when(consumeXmlService.consumeXml()).thenReturn(Arrays.asList("Lanterna Verde"));
        when(jogadorRepository.findByCodinome("Lanterna Verde")).thenReturn(jogador);

        Exception codinomeIndisponivel = Assertions.assertThrows(Exception.class, () -> {
            jogadorService.createJogador(jogadorDTO);
        });

        Assertions.assertEquals("Não existem mais usuários da liga da justiça disponíveis.", codinomeIndisponivel.getMessage());
    }


}