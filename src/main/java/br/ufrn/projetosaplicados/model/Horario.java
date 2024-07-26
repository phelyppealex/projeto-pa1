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
    private String hora;

    @Data
    public static class DtoRequest {
        private String id;
        private String hora;
        
        public static Horario convertToEntity(Horario.DtoRequest dto,  ModelMapper mapper){
            return mapper.map(dto, Horario.class);
        }
    }

    @Data
    public static class DtoResponse {
        private String id;
        private String hora;
        
        public static Horario.DtoResponse convertToDto(Horario horario,  ModelMapper mapper){
            return mapper.map(horario, Horario.DtoResponse.class);
        }
    }
}
