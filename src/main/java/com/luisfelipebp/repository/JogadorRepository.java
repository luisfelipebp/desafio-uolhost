package com.luisfelipebp.repository;

import com.luisfelipebp.model.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JogadorRepository extends JpaRepository<Jogador, Long> {

    @Override
    Optional<Jogador> findById(Long id);

    Jogador findByCodinome(String codinome);
}
