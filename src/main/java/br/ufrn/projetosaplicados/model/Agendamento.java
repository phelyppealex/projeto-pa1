package br.ufrn.projetosaplicados.model;
import org.modelmapper.ModelMapper;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne
    DiaSemana dia;

    @OneToOne
    Horario horario;

    @OneToOne
    Servico servico;
    
    public static class DtoRequest{
        DiaSemana dia;
        Horario horario;
        Servico servico;

        public static Agendamento convertToEntity(Agendamento.DtoRequest dto, ModelMapper mapper){
            return mapper.map(dto, Agendamento.class);
        }
    }

    public static class DtoResponse{
        String id;
        DiaSemana dia;
        Horario horario;
        Servico servico;

        public static Agendamento.DtoResponse convertToDto(Agendamento agendamento, ModelMapper mapper){
            return mapper.map(agendamento, Agendamento.DtoResponse.class);
        }
    }
}
