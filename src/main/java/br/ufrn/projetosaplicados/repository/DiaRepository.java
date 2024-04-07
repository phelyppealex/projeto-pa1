package br.ufrn.projetosaplicados.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufrn.projetosaplicados.model.Dia;

public interface DiaRepository extends JpaRepository<Dia, String> {}