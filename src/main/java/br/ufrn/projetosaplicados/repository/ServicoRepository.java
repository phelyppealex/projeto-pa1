package br.ufrn.projetosaplicados.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufrn.projetosaplicados.model.Servico;

public interface ServicoRepository extends JpaRepository<Servico, String> {}