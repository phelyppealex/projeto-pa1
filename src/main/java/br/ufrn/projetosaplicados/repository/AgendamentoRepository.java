package br.ufrn.projetosaplicados.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.ufrn.projetosaplicados.model.Agendamento;
import br.ufrn.projetosaplicados.model.Usuario;

import java.util.List;


public interface AgendamentoRepository extends JpaRepository<Agendamento, String> {
    List<Agendamento> findByUsuario(Usuario usuario);
}