package br.ufrn.projetosaplicados.service;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import br.ufrn.projetosaplicados.model.Dia;
import br.ufrn.projetosaplicados.repository.DiaRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class DiaService {
    DiaRepository repository;
    
    public DiaService(DiaRepository repository){
        this.repository = repository;
    }

    public Dia save(Dia dia){
        return this.repository.save(dia);
    }

    public void deleteById(String id){
        findByID(id);
        this.repository.deleteById(id);
    }

    public void delete(Dia dia){
        this.repository.delete(dia);
    }

    public Dia findByID(String id){
        Optional<Dia> dia = this.repository.findById(id);

        if(dia.isPresent())
            return dia.get();

        throw new EntityNotFoundException();
    }

    public List<Dia> findAll(){
        return this.repository.findAll();
    }

    public void update(Dia dia){
        var temp = findByID(dia.getId());
        delete(temp);
        save(dia);
    }
}