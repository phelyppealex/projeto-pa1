package br.ufrn.projetosaplicados.model;
import java.util.List;

import org.modelmapper.ModelMapper;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Data
@Entity
public class DiaSemana {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    private String dia;
    private boolean disponivel;

    @ManyToMany(cascade = CascadeType.ALL)
    List<Horario> horarios;

    @Data
    public static class DtoRequest {
        private String dia;
        private boolean disponivel;
        List<Horario> horarios;
        
        public static DiaSemana convertToEntity(DiaSemana.DtoRequest dto,  ModelMapper mapper){
            return mapper.map(dto, DiaSemana.class);
        }
    }

    @Data
    public static class DtoResponse {
        private String id;
        private String dia;
        private boolean disponivel;
        List<Horario> horarios;
        
        public static DiaSemana.DtoResponse convertToDto(DiaSemana dia,  ModelMapper mapper){
            return mapper.map(dia, DiaSemana.DtoResponse.class);
        }
    }
}
