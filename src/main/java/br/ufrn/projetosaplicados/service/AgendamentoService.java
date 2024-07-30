package br.ufrn.projetosaplicados.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.ufrn.projetosaplicados.model.Agendamento;
import br.ufrn.projetosaplicados.repository.AgendamentoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AgendamentoService {
    AgendamentoRepository repository;

    public AgendamentoService(AgendamentoRepository aRepo){
        this.repository = aRepo;
    }

    public void save(Agendamento a){
        this.repository.save(a);
    }

    public Agendamento findById(String id){
        Optional<Agendamento> a = this.repository.findById(id);

        if(a.isPresent()) return a.get();

        throw new EntityNotFoundException();
    }

    public List<Agendamento> findAll(){
        return this.repository.findAll();
    }

    public void deleteById(String id){
        findById(id);
        this.repository.deleteById(id);
    }

    public void delete(Agendamento a){
        this.repository.delete(a);
    }

    public void update(Agendamento a){
        var temp = findById(a.getId());
        delete(temp);
        save(a);
    }

}
