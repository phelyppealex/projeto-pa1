package br.ufrn.projetosaplicados.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.ufrn.projetosaplicados.model.Horario;
import br.ufrn.projetosaplicados.repository.HorarioRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class HorarioService {
    HorarioRepository repository;
    
    public HorarioService(HorarioRepository repository){
        this.repository = repository;
    }

    public Horario save(Horario horario){
        return this.repository.save(horario);
    }

    public Horario findById(String id){
        Optional<Horario> horario = this.repository.findById(id);

        if(horario.isPresent())
            return this.findById(id);
        
        throw new EntityNotFoundException();
    }

    public List<Horario> findAll(){
        return this.repository.findAll();
    }

    public void deleteById(String id){
        findById(id);
        this.repository.deleteById(id);
    }

    public void delete(Horario horario){
        this.repository.delete(horario);
    }
}