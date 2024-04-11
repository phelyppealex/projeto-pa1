package br.ufrn.projetosaplicados.model;
import org.modelmapper.ModelMapper;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class DiaSemana {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private int dia;
    private int mes;
    private int ano;

    public static class DtoRequest {
        private int dia;
        private int mes;
        private int ano;
        
        public static DiaSemana convertToEntity(DiaSemana.DtoRequest dto,  ModelMapper mapper){
            return mapper.map(dto, DiaSemana.class);
        }
    }

    public static class DtoResponse {
        private String id;
        private int dia;
        private int mes;
        private int ano;
        
        public static DiaSemana.DtoResponse convertToDto(DiaSemana dia,  ModelMapper mapper){
            return mapper.map(dia, DiaSemana.DtoResponse.class);
        }
    }
}
