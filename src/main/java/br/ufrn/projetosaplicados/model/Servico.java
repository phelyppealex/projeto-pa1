package br.ufrn.projetosaplicados.model;
import java.util.List;

import org.modelmapper.ModelMapper;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String nome;
    private double preco;
    private String linkFoto;

    @OneToMany
    List<DiaSemana> dias;

    @Data
    public static class DtoRequest {
        private String nome;
        private double preco;
        private String linkFoto;
        
        public static Servico convertToEntity(Servico.DtoRequest dto,  ModelMapper mapper){
            return mapper.map(dto, Servico.class);
        }
    }

    @Data
    public static class DtoResponse {
        private String id;
        private String nome;
        private double preco;
        private String linkFoto;
        
        public static Servico.DtoResponse convertToDto(Servico servico,  ModelMapper mapper){
            return mapper.map(servico, Servico.DtoResponse.class);
        }
    }
    
}
