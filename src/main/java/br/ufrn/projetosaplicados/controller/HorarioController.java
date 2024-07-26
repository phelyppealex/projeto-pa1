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

import br.ufrn.projetosaplicados.model.Horario;
import br.ufrn.projetosaplicados.service.HorarioService;


@RestController
@RequestMapping("/horario")
public class HorarioController {

    private ModelMapper mapper;
    private HorarioService service;

    public HorarioController(ModelMapper mapper, HorarioService service){
        this.mapper = mapper;
        this.service = service;
    }

    @GetMapping
    public List<Horario.DtoResponse> findAllHorarios() {
        List<Horario.DtoResponse> horario = this.service.findAll().stream().map(
            hour -> Horario.DtoResponse.convertToDto(hour, mapper)
        ).toList();
        return horario;
    }

    @GetMapping("/{id}")
    public Horario findById(@PathVariable String id) {
        return this.service.findById(id);
    }
    

    @PostMapping
    public void saveHorario(@RequestBody Horario.DtoRequest horarioDto) {
        Horario horario = Horario.DtoRequest.convertToEntity(horarioDto, mapper);
        this.service.save(horario);
    }
    
    @DeleteMapping("/{id}")
    public void deleteHorario(@PathVariable String id){
        this.service.deleteById(id);
    }
    
    @PutMapping
    public void updateHorario(@RequestBody Horario horario){
        this.service.update(horario);
    }
}