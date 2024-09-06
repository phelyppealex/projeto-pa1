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
        // Resultado de uma SQL injetada
        var result = this.repository.findAvailableHorariosPerDiaSemana();

        // Lista que ao final será retornada
        var dias = new ArrayList<DiaSemana>();

        // Salvará os IDs que já foram adicionados a lista final
        var diaIds = new ArrayList<String>();
        
        // Percorre o resultado da SQL
        for(Object[] linha: result){
            // Verifica se o objeto de dia referente ao ID contido na linda do result já foi criado na lista final
            if(!diaIds.contains((String) linha[0])){
                var horario = new Horario();
                horario.setId(linha[2].toString());
                horario.setHora(linha[3].toString());
                
                var dia = new DiaSemana();
                dia.setId(linha[0].toString());
                dia.setDia(linha[1].toString());
                dia.setHorarios(new ArrayList<>());
                dia.getHorarios().add(horario);

                // Lista final
                dias.add(dia);
                // Lista de IDs
                diaIds.add(dia.getId());
            }else{
                for(DiaSemana dia: dias){
                    if(dia.getId() .equals(linha[0].toString())){
                        var horario = new Horario();
                        horario.setId(linha[2].toString());
                        horario.setHora(linha[3].toString());

                        dia.getHorarios().add(horario);
                        break;
                    }
                }
            }
        }

        return dias;
    }
}