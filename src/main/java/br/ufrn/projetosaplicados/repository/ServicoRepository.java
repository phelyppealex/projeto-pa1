package br.ufrn.projetosaplicados.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ufrn.projetosaplicados.model.Servico;

public interface ServicoRepository extends JpaRepository<Servico, String> {
    @Query(value = "SELECT * FROM servico ORDER BY nome ASC", nativeQuery=true)
    List<Servico> findAllByOrderByNomeAsc();
}