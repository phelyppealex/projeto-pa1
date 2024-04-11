package br.ufrn.projetosaplicados.model;
import org.modelmapper.ModelMapper;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String nome;
    private double preco;

    public static class DtoRequest {
        private String nome;
        private double preco;
        
        public static Servico convertToEntity(Servico.DtoRequest dto,  ModelMapper mapper){
            return mapper.map(dto, Servico.class);
        }
    }

    public static class DtoResponse {
        private String id;
        private String nome;
        private double preco;
        
        public static Servico.DtoResponse convertToDto(Servico servico,  ModelMapper mapper){
            return mapper.map(servico, Servico.DtoResponse.class);
        }
    }
    
}
