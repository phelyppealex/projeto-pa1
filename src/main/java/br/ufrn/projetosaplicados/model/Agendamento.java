package br.ufrn.projetosaplicados.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Agendamento {
    @OneToOne
    DiaSemana dia;

    @OneToOne
    Horario horario;

    @OneToOne
    Servico servico;
}
