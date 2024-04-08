package br.ufrn.projetosaplicados.model;
import org.modelmapper.ModelMapper;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Dia {
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
        
        public static Dia convertToEntity(Dia.DtoRequest dto,  ModelMapper mapper){
            return mapper.map(dto, Dia.class);
        }
    }

    public static class DtoResponse {
        private String id;
        private int dia;
        private int mes;
        private int ano;
        
        public static Dia.DtoResponse convertToDto(Dia dia,  ModelMapper mapper){
            return mapper.map(dia, Dia.DtoResponse.class);
        }
    }
}
