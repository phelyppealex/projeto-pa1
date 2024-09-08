package br.ufrn.projetosaplicados.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufrn.projetosaplicados.model.Agendamento;
import br.ufrn.projetosaplicados.model.Usuario;


public interface AgendamentoRepository extends JpaRepository<Agendamento, String> {
    List<Agendamento> findByUsuario(Usuario usuario);
}