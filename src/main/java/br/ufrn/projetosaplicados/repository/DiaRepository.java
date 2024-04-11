package br.ufrn.projetosaplicados.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufrn.projetosaplicados.model.DiaSemana;

public interface DiaRepository extends JpaRepository<DiaSemana, String> {}