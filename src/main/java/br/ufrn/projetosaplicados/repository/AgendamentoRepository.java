package br.ufrn.projetosaplicados.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import br.ufrn.projetosaplicados.model.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, String> {}