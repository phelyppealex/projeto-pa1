package br.ufrn.projetosaplicados.service;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import br.ufrn.projetosaplicados.model.Servico;
import br.ufrn.projetosaplicados.repository.ServicoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ServicoService {
    ServicoRepository repository;

    public ServicoService(ServicoRepository repository){
        this.repository = repository;
    }

    public Servico save(Servico dia){
        return this.repository.save(dia);
    }

    public Servico findById(String id){
        Optional<Servico> horario = this.repository.findById(id);

        if(horario.isPresent()) return horario.get();
        
        throw new EntityNotFoundException();
    }

    public List<Servico> findAll(){
        return this.repository.findAll();
    }

    public void deleteById(String id){
        findById(id);
        this.repository.deleteById(id);
    }

    public void delete(Servico servico){
        this.repository.delete(servico);
    }

    public void update(Servico servico){
        var temp = findById(servico.getId());
        delete(temp);
        save(servico);
    }
}
