package br.ufrn.projetosaplicados.service;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.ufrn.projetosaplicados.model.DiaSemana;
import br.ufrn.projetosaplicados.repository.DiaRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class DiaService {
    DiaRepository repository;
    
    public DiaService(DiaRepository repository){
        this.repository = repository;
    }

    public DiaSemana save(DiaSemana dia){
        return this.repository.save(dia);
    }

    public void deleteById(String id){
        findById(id);
        this.repository.deleteById(id);
    }

    public void delete(DiaSemana dia){
        this.repository.delete(dia);
    }

    public DiaSemana findById(String id){
        Optional<DiaSemana> dia = this.repository.findById(id);

        if(dia.isPresent()) return dia.get();

        throw new EntityNotFoundException();
    }

    public List<DiaSemana> findAll(){
        return this.repository.findAll();
    }

    public void update(DiaSemana dia){
        save(dia);
    }
}