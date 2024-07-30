package br.ufrn.projetosaplicados.controller;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.projetosaplicados.model.DiaSemana;
import br.ufrn.projetosaplicados.service.DiaService;
import br.ufrn.projetosaplicados.model.Servico;


@RestController
@RequestMapping("/dia")
public class DiaController {

    private DiaService service;
    private ModelMapper mapper;

    public DiaController(DiaService service, ModelMapper mapper){
        this.mapper = mapper;
        this.service = service;
    }

    @GetMapping
    public List<DiaSemana.DtoResponse> findAllDias() {
        List<DiaSemana.DtoResponse> dia = this.service.findAll().stream().map(
            day -> DiaSemana.DtoResponse.convertToDto(day, mapper)
        ).toList();
        return dia;
    }

    @GetMapping("/{id}")
    public DiaSemana findById(@PathVariable String id) {
        return this.service.findById(id);
    }
    

    @PostMapping
    public void saveDia(@RequestBody DiaSemana.DtoRequest diaDto) {
        DiaSemana dia = DiaSemana.DtoRequest.convertToEntity(diaDto, mapper);
        this.service.save(dia);
    }
    
    @DeleteMapping("/{id}")
    public void deleteDia(@PathVariable String id){
        this.service.deleteById(id);
    }
    
    @PutMapping
    public void updateDia(@RequestBody DiaSemana dia){
        this.service.update(dia);
    }
}
