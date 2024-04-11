package br.ufrn.projetosaplicados.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufrn.projetosaplicados.model.Horario;

public interface HorarioRepository extends JpaRepository<Horario, String> {}