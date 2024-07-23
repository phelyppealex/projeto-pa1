package br.ufrn.projetosaplicados.model;
import org.modelmapper.ModelMapper;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private int horas;
    private int minutos;

    @Data
    public static class DtoRequest {
        private int horas;
        private int minutos;
        
        public static Horario convertToEntity(Horario.DtoRequest dto,  ModelMapper mapper){
            return mapper.map(dto, Horario.class);
        }
    }

    public static class DtoResponse {
        private String id;
        private int horas;
        private int minutos;
        
        public static Horario.DtoResponse convertToDto(Horario horario,  ModelMapper mapper){
            return mapper.map(horario, Horario.DtoResponse.class);
        }
    }
}
