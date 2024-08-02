package br.ufrn.projetosaplicados.model;
import org.modelmapper.ModelMapper;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    DiaSemana dia;

    @ManyToOne
    Horario horario;

    @ManyToOne
    Servico servico;

    @ManyToOne
    Usuario usuario;
    
    @Data
    public static class DtoRequest{
        String dia;
        String horario;
        String servico;
        String usuario;

        public static Agendamento convertToEntity(Agendamento.DtoRequest dto, ModelMapper mapper){
            return mapper.map(dto, Agendamento.class);
        }
    }

    @Data
    public static class DtoResponse{
        String id;
        DiaSemana dia;
        Horario horario;
        Servico servico;
        Usuario usuario;

        public static Agendamento.DtoResponse convertToDto(Agendamento agendamento, ModelMapper mapper){
            return mapper.map(agendamento, Agendamento.DtoResponse.class);
        }
    }
}
