package br.ufrn.projetosaplicados.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ufrn.projetosaplicados.model.DiaSemana;

public interface DiaRepository extends JpaRepository<DiaSemana, String> {
    @Query(
        value= "SELECT ds.id AS dia_semana_id, ds.dia AS dia_semana_nome, h.id AS horario_id, h.hora AS horario_hora " +
        "FROM dia_semana ds " +
        "JOIN dia_semana_horarios dsh ON ds.id = dsh.dia_semana_id " +
        "JOIN horario h ON dsh.horarios_id = h.id " +
        "WHERE h.id NOT IN (SELECT a.horario_id FROM agendamento a WHERE a.dia_id = ds.id) " +
        "ORDER BY ds.id, h.hora;",
        nativeQuery = true
    )
    List<Object[]> findAvailableHorariosPerDiaSemana();
}