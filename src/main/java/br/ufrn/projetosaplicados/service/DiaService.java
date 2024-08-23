package br.ufrn.projetosaplicados.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.ufrn.projetosaplicados.model.DiaSemana;
import br.ufrn.projetosaplicados.model.Horario;
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

    public List<DiaSemana> findAvailableHorariosPerDiaSemana(){
        List<Object[]> result = this.repository.findAvailableHorariosPerDiaSemana();

        List<String> diaIds = new ArrayList<String>();

        for(Object[] linha: result){
            if(!diaIds.contains(linha[0])){
                diaIds.add(linha[0].toString());
            }
        }

        List<DiaSemana> dias = new ArrayList<DiaSemana>();

        for(String idDia: diaIds){
            DiaSemana d = new DiaSemana();
            d.setId(idDia);
            for(Object[] linha: result){
                Horario h;
                if(linha[0].equals(idDia)){
                    h = new Horario();
                    d.setDia(linha[1].toString());

                    h.setId(linha[2].toString());
                    h.setHora(linha[3].toString());
                    
                    List<Horario> listaHorarios;
                    if(d.getHorarios() != null){
                        listaHorarios = d.getHorarios();
                        listaHorarios.add(h);
                    }else{
                        listaHorarios = new ArrayList<Horario>();
                    }
                    d.setHorarios(listaHorarios);
                }
            }
            dias.add(d);
        }

        return dias;
    }
}